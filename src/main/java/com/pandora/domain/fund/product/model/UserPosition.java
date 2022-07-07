package com.pandora.domain.fund.product.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserPosition {

    private String fundName;

    private BigDecimal currentAmount;

    private BigDecimal yesterdayProfit;

    private BigDecimal allProfit;

    private BigDecimal yield;

    private Boolean hasUnLiquidation;

    public static UserPosition build(String fundName, BigDecimal currentAmount, BigDecimal yesterdayProfit,
                                     BigDecimal allProfit, BigDecimal yield) {
        UserPosition userPosition = new UserPosition();
        userPosition.setFundName(fundName);
        userPosition.setCurrentAmount(currentAmount);
        userPosition.setYesterdayProfit(yesterdayProfit);
        userPosition.setAllProfit(allProfit);
        userPosition.setYield(yield);
        return userPosition;
    }
}
