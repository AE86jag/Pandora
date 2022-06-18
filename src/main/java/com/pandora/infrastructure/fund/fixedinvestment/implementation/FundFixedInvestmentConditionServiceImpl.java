package com.pandora.infrastructure.fund.fixedinvestment.implementation;

import com.google.common.collect.Lists;
import com.pandora.domain.fund.fixedinvestment.mapper.FundFixedInvestmentConditionMapper;
import com.pandora.domain.fund.fixedinvestment.mapper.FundFixedInvestmentConditionRecordMapper;
import com.pandora.domain.fund.fixedinvestment.mapper.PostponeRecordMapper;
import com.pandora.domain.fund.fixedinvestment.model.ConditionSearchParameter;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentConditionRecord;
import com.pandora.domain.fund.fixedinvestment.model.PostponeRecord;
import com.pandora.domain.fund.fixedinvestment.service.IFundFixedInvestmentConditionService;
import com.pandora.domain.fund.product.mapper.NavMapper;
import com.pandora.domain.fund.product.model.Nav;
import com.pandora.domain.holiday.mapper.TradeDayMapper;
import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.eastmoney.EastMoneyClient;
import com.pandora.infrastructure.eastmoney.NavEstimateDTO;
import com.pandora.infrastructure.exception.BusinessException;
import com.pandora.infrastructure.notify.EmailSender;
import com.pandora.infrastructure.notify.FixedEmailInvestmentMessage;
import com.pandora.infrastructure.notify.FixedInvestmentEmailNotify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class FundFixedInvestmentConditionServiceImpl implements IFundFixedInvestmentConditionService {

    @Autowired
    private FundFixedInvestmentConditionMapper fundFixedInvestmentConditionMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TradeDayMapper tradeDayMapper;


    @Autowired
    private PostponeRecordMapper postponeRecordMapper;

    @Autowired
    private EastMoneyClient eastMoneyClient;

    @Autowired
    private FundFixedInvestmentConditionTransactionService fundFixedInvestmentConditionTransactionService;

    @Autowired
    private FundFixedInvestmentConditionRecordMapper fundFixedInvestmentConditionRecordMapper;

    @Autowired
    private NavMapper navMapper;

    @Override
    public void createFundFixedInvestmentCondition(FundFixedInvestmentCondition fundFixedInvestmentCondition) {
        int count = fundFixedInvestmentConditionMapper.findCountByUserIdAndFundCode(
                fundFixedInvestmentCondition.getFundCode(), CurrentUserUtils.currenUserId());
        if (count != 0) {
            throw new RuntimeException("该基金条件单已存在");
        }
        fundFixedInvestmentConditionMapper.insert(fundFixedInvestmentCondition);
    }

    @Override
    public List<FundFixedInvestmentCondition> getUserConditionsPage(int page, int size) {
        return fundFixedInvestmentConditionMapper.findPageByUserId(page * size, size, CurrentUserUtils.currenUserId());
    }

    @Override
    public void changeStatusById(String id, Integer status) {
        FundFixedInvestmentCondition condition = fundFixedInvestmentConditionMapper.findById(id);
        if (condition == null) {
            throw new RuntimeException("条件单不存在，请刷新重试");
        }

        if (!condition.getUserId().equals(CurrentUserUtils.currenUserId())) {
            throw new RuntimeException("该条件单不属于您");
        }
        if (status.equals(condition.getStatus())) {
            throw new RuntimeException("该条件单已修改，无需修改");
        }
        fundFixedInvestmentConditionMapper.updateStatusById(status, id);
    }

    @Override
    public void messageNotify() {
        FixedEmailInvestmentMessage message = new FixedEmailInvestmentMessage(
                Lists.newArrayList("754244523@qq.com", "15717003806@163.com"),
                "消息通知测试", "这是一个内容，内容很长很长很长很长很长长长长长长长长长长长长");
        EmailSender sender = new EmailSender(javaMailSender);
        new FixedInvestmentEmailNotify(Lists.newArrayList(sender)).send(Lists.newArrayList(message));
    }

    /**
     * 每天下午两点，定时触发条件单
     */
    @Scheduled(cron = "0 0 14 * * ?")
    public void conditionExecution() {
        LocalDate now = LocalDate.now();

        Boolean isWorkDay = tradeDayMapper.isWorkDay(now);
        if (isWorkDay == null) {
            throw new BusinessException("节假日表未配置", true);
        }

        //获取当前周期和到达市值恒定的条件单
        List<FundFixedInvestmentCondition> conditions =
                fundFixedInvestmentConditionMapper.findByCycle(ConditionSearchParameter.from(now));
        if(CollectionUtils.isEmpty(conditions)) {
            return;
        }

        if (!isWorkDay) {
            LocalDate nextTradeDate = tradeDayMapper.findNextTradeDay(now);
            List<PostponeRecord> postponeRecords = PostponeRecord.from(conditions, nextTradeDate);
            postponeRecordMapper.batchInsert(postponeRecords);
            return;
        }

        for (FundFixedInvestmentCondition condition : conditions) {
            fundFixedInvestmentConditionTransactionService.executePerCondition(condition, false);
        }
    }

    /**
     * 时间策略是每天早上8-12点每小时执行一次
     * 定时清算基金净值，更新个人持仓
     */
    @Scheduled(cron = "0 0 8-12 * * ?")
    public void liquidationExecution () {
        List<LocalDate> aheadTradeDates = tradeDayMapper.findTwoAheadTradeDay(LocalDate.now());
        LocalDate preTradeDate = aheadTradeDates.get(0);
        LocalDate twoAheadTradeDate = aheadTradeDates.get(1);

        Set<String> fundCodes = fundFixedInvestmentConditionMapper.findAllFundCodes();

        for (String fundCode : fundCodes) {
            try {
                //TODO 后面找一个批量获取净值的接口
                NavEstimateDTO nav = eastMoneyClient.getNavByCode(fundCode);
                if (!nav.getNavLocalDate().equals(preTradeDate)) {
                    continue;
                }
                //如果已清算，更新数据库，防止数据库数据和接口查询的不一致
                Nav navEntity = Nav.build(fundCode, preTradeDate, nav.getUnitNav());
                navMapper.saveOrUpdate(navEntity);

                LocalDateTime begin = twoAheadTradeDate.atTime(15,0,0);
                LocalDateTime end = preTradeDate.atTime(15,0,0);
                List<FundFixedInvestmentConditionRecord> records = fundFixedInvestmentConditionRecordMapper
                        .findByFundCodeAndIsLiquidationAndDate(fundCode, false, begin, end);

                for (FundFixedInvestmentConditionRecord record : records) {
                    fundFixedInvestmentConditionTransactionService
                            .doLiquidation(record, nav.getUnitNav());
                }
            } catch (Exception e) {
                log.error("sync nav error, fund code: {}", fundCode, e);
            }
        }
    }

    /**
     * 定时处理延迟到下一交易日的定投任务
     */
    @Scheduled(cron = "0 0 14 * * ?")
    public void postponeExecution() {
        LocalDate now = LocalDate.now();
        Boolean isWorkDay = tradeDayMapper.isWorkDay(now);
        if (!isWorkDay) {
            return;
        }
        List<FundFixedInvestmentCondition> conditions =
                fundFixedInvestmentConditionMapper.findPostponeConditionByDate(now);
        for (FundFixedInvestmentCondition condition : conditions) {
            fundFixedInvestmentConditionTransactionService.executePerCondition(condition, true);
        }
    }


}
