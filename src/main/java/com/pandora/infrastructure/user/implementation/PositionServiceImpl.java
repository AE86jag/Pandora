package com.pandora.infrastructure.user.implementation;

import com.google.common.collect.Lists;
import com.pandora.domain.fund.fixedinvestment.mapper.FundFixedInvestmentConditionRecordMapper;
import com.pandora.domain.fund.product.mapper.NavMapper;
import com.pandora.domain.fund.product.model.Nav;
import com.pandora.domain.fund.product.model.UserPosition;
import com.pandora.domain.holiday.mapper.TradeDayMapper;
import com.pandora.domain.user.mapper.PositionMapper;
import com.pandora.domain.user.model.Position;
import com.pandora.domain.user.service.IPositionService;
import com.pandora.infrastructure.exception.BusinessException;
import com.pandora.infrastructure.exception.ExceptionRecord;
import com.pandora.infrastructure.exception.ExceptionRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Service
@Slf4j
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private NavMapper navMapper;

    @Autowired
    private FundFixedInvestmentConditionRecordMapper fundFixedInvestmentConditionRecordMapper;

    @Autowired
    private TradeDayMapper tradeDayMapper;

    @Autowired
    private ExceptionRecordService exceptionRecordService;

    @Override
    public List<UserPosition> calculateUserPosition(String userId) {
        List<Position> positions = positionMapper.findByUserId(userId);
        if (CollectionUtils.isEmpty(positions)) {
            return null;
        }
        List<UserPosition> userPositions = Lists.newArrayList();
        for (Position position : positions) {
            //金额：持有份额 * 最新净值 （如果当天没有清算，可以标记下未清算）
            //昨日收益：（最新净值-前一天净值）* 份额（如果前一个交易日未清算，这个值就是前天收益）
            //同一个基金已清算的交易记录对交易金额求和，用当前市值减去求和的值，用差值除以交易金额总和

            UserPosition userPosition = new UserPosition();
            userPosition.setFundName(position.getFundName());

            LocalDate now = LocalDate.now();
            List<LocalDate> twoAheadTradeDays = tradeDayMapper.findTwoAheadTradeDay(now);

            List<Nav> navs = navMapper.findByFundCodeAndDates(position.getFundCode(), twoAheadTradeDays);
            if (navs.size() == 0) {
                exceptionRecordService.save(BusinessException.class.getName(), position.getFundCode(),
                        position.getFundName(),
                        "com.pandora.infrastructure.user.implementation.calculateUserPosition(String userId)",
                        true);
                continue;
            }

            BigDecimal share = position.getShare();
            BigDecimal currentAmount = share.multiply(navs.get(0).getNav());
            userPosition.setCurrentAmount(currentAmount.setScale(2, BigDecimal.ROUND_HALF_UP));

            //TODO 待验证
            if (navs.size() == 2 && navs.get(0).getNavDate().compareTo(twoAheadTradeDays.get(0)) == 0) {
                //已清算
                BigDecimal yesterdayProfit = share.multiply(navs.get(0).getNav().subtract(navs.get(1).getNav()));
                userPosition.setYesterdayProfit(yesterdayProfit.setScale(2, BigDecimal.ROUND_HALF_UP));
            }

            BigDecimal cumulativeAmount = fundFixedInvestmentConditionRecordMapper
                    .findHasLiquidationSumAmountByUserIdAndFundCode(userId, position.getFundCode());

            BigDecimal allProfit = currentAmount.subtract(cumulativeAmount);
            userPosition.setAllProfit(allProfit.setScale(2, BigDecimal.ROUND_HALF_UP));

            BigDecimal yield = allProfit.divide(cumulativeAmount, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal("100"));
            userPosition.setYield(yield.setScale(2, BigDecimal.ROUND_HALF_UP));

            Integer count = fundFixedInvestmentConditionRecordMapper
                    .findUnLiquidationCountByUserIdAndFundCode(userId, position.getFundCode());
            userPosition.setHasUnLiquidation(count != null && count > 0);

            userPositions.add(userPosition);
        }

        return userPositions;
    }
}
