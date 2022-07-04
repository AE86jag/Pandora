package com.pandora.infrastructure.fund.fixedinvestment.implementation;

import com.google.common.collect.Lists;
import com.pandora.domain.fund.fixedinvestment.mapper.FundFixedInvestmentConditionMapper;
import com.pandora.domain.fund.fixedinvestment.mapper.FundFixedInvestmentConditionRecordMapper;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentConditionRecord;
import com.pandora.domain.fund.product.mapper.NavMapper;
import com.pandora.domain.fund.product.model.Nav;
import com.pandora.domain.user.mapper.PositionMapper;
import com.pandora.domain.user.model.Position;
import com.pandora.infrastructure.eastmoney.EastMoneyClient;
import com.pandora.infrastructure.exception.ExceptionRecordService;
import com.pandora.infrastructure.notify.EmailSender;
import com.pandora.infrastructure.notify.FixedEmailInvestmentMessage;
import com.pandora.infrastructure.notify.FixedInvestmentEmailNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ZERO;

@Service
public class FundFixedInvestmentConditionTransactionService {

    @Autowired
    private FundFixedInvestmentConditionMapper fundFixedInvestmentConditionMapper;

    @Autowired
    private EastMoneyClient eastMoneyClient;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private FundFixedInvestmentConditionRecordMapper fundFixedInvestmentConditionRecordMapper;


    @Autowired
    private JavaMailSender javaMailSender;

    @Transactional
    public void executePerCondition(FundFixedInvestmentCondition condition, Boolean isPostpone) {
        BigDecimal estimateNav = eastMoneyClient.getNavEstimateByCode(condition.getFundCode());
        Position position =
                positionMapper.getPositionByFundCodeAndUserId(condition.getUserId(), condition.getFundCode());
        BigDecimal currentAmount = position == null ? ZERO : position.calculationAmount(estimateNav);

        BigDecimal toBuyAmount = null;

        if (!condition.getIsMaintain() && condition.isDubbleWeek()) {
            //定投阶段，如果是双周定投，判断最近一次定投时间是不是间隔一个星期
            FundFixedInvestmentConditionRecord record =
                    fundFixedInvestmentConditionRecordMapper.findLatestRecordByConditionId(condition.getId());
            //TODO 如果后续条件有修改功能，这个判断两个日期是否在同一周不准确
            if(!record.getCreatedTime().toLocalDate().equals(LocalDate.now().minusWeeks(2))) {
                return;
            }
        }

        int result = currentAmount.compareTo(condition.getTargetMarketValue());
        if (!condition.getIsMaintain() && result < 0) {
            Integer count = fundFixedInvestmentConditionRecordMapper
                    .findCountByConditionIdAndIsMaintain(condition.getId(), false);
            toBuyAmount = condition.calculationBuyAmount(count, currentAmount);
        }

        if (condition.getIsMaintain() || result > 0) {
            toBuyAmount = condition.calculateAmountWhenIsMaintain(currentAmount);
            if (!condition.getIsMaintain() && result > 0) {
                //第一次达到目标市值，更新is_maintain
                fundFixedInvestmentConditionMapper.updateIsMaintainById(condition.getId(), true);
            }
        }

        if (toBuyAmount == null || toBuyAmount.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        FundFixedInvestmentConditionRecord record =
                FundFixedInvestmentConditionRecord.from(condition, isPostpone, toBuyAmount, condition.getIsMaintain());
        fundFixedInvestmentConditionRecordMapper.insert(record);

        FixedEmailInvestmentMessage message = FixedEmailInvestmentMessage.from(condition, toBuyAmount);
        EmailSender sender = new EmailSender(javaMailSender);
        new FixedInvestmentEmailNotify(Lists.newArrayList(sender)).send(Lists.newArrayList(message));
    }

    @Transactional
    public void doLiquidation(FundFixedInvestmentConditionRecord record, BigDecimal nav) {
        BigDecimal share = record.getAmount().divide(nav, 2, ROUND_HALF_UP);

        Integer positionCount = positionMapper.findCountByUserIdAndFundCode(record.getUserId(), record.getFundCode());
        if (positionCount == null || positionCount == 0) {
            positionMapper.insert(
                    Position.build(record.getUserId(), record.getFundCode(), record.getFundName(), share));
        } else {
            positionMapper.updateShareByUserIdAndFundCode(record.getUserId(), record.getFundCode(), share);
        }

        fundFixedInvestmentConditionRecordMapper.updateIsLiquidationById(record.getId(), true);
    }
}
