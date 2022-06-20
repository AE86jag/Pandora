package com.pandora.infrastructure.user.implementation;

import com.google.common.collect.Lists;
import com.pandora.domain.fund.fixedinvestment.mapper.FundFixedInvestmentConditionRecordMapper;
import com.pandora.domain.fund.product.mapper.NavMapper;
import com.pandora.domain.fund.product.model.UserPosition;
import com.pandora.domain.user.mapper.PositionMapper;
import com.pandora.domain.user.model.Position;
import com.pandora.domain.user.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;


@Service
public class PositionServiceImpl implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private NavMapper navMapper;

    @Autowired
    private FundFixedInvestmentConditionRecordMapper fundFixedInvestmentConditionRecordMapper;

    @Override
    public List<UserPosition> calculateUserPosition(String userId) {
        List<Position> positions = positionMapper.findByUserId(userId);
        if (CollectionUtils.isEmpty(positions)) {
            return null;
        }
        List<UserPosition> userPositions = Lists.newArrayList();
        for (Position position : positions) {
            //金额：持有份额 * 最新净值 （如果当天没有清算，可以标记下未清算）
            //昨日收益：（最新净值-前一天净值）* 份额
            //同一个基金交易记录对交易金额求和，用当前市值减去求和的值，用差值除以交易金额总和

            UserPosition userPosition = new UserPosition();
            userPosition.setFundName(position.getFundName());

            List<BigDecimal> navs = navMapper.findLatestTwoNavByFundCode(position.getFundCode());
            BigDecimal share = position.getShare();
            BigDecimal currentAmount = share.multiply(navs.get(0));
            userPosition.setCurrentAmount(currentAmount.setScale(2, BigDecimal.ROUND_HALF_UP));

            BigDecimal yesterdayProfit = share.multiply(navs.get(0).subtract(navs.get(1)));
            userPosition.setYesterdayProfit(yesterdayProfit.setScale(2, BigDecimal.ROUND_HALF_UP));

            BigDecimal cumulativeAmount = fundFixedInvestmentConditionRecordMapper
                    .findSumAmountByUserIdAndFundCode(userId, position.getFundCode());

            BigDecimal allProfit = currentAmount.subtract(cumulativeAmount);
            userPosition.setAllProfit(allProfit.setScale(2, BigDecimal.ROUND_HALF_UP));

            BigDecimal yield = allProfit.divide(cumulativeAmount, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal("100"));
            userPosition.setYield(yield.setScale(2, BigDecimal.ROUND_HALF_UP));

            userPositions.add(userPosition);
        }

        return userPositions;
    }
}
