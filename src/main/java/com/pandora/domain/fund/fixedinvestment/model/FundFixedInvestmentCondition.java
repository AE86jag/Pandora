package com.pandora.domain.fund.fixedinvestment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ZERO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FundFixedInvestmentCondition {
    private String id;
    private String fundCode;
    private String fundName;
    private BigDecimal targetMarketValue;
    private FixedInvestmentCycleFirstCategory firstCategoryOfCycle;
    private FixedInvestmentCycleSecondCategory secondCategoryOfCycle;
    private BigDecimal positiveIncrease;
    private BigDecimal negativeIncrease;
    private BigDecimal perAmount;
    private String email;
    private String userId;
    private Integer status;
    private Boolean isMaintain;
    private LocalDateTime createdTime;
    private LocalDateTime lastModifiedTime;


    public String getCycleDescription() {
        return firstCategoryOfCycle.getDescription() + secondCategoryOfCycle.getDescription();
    }

    public BigDecimal calculationBuyAmount(Integer count, BigDecimal currentAmount) {
        BigDecimal result = new BigDecimal(count + 1).multiply(perAmount).subtract(currentAmount);
        return result.setScale(2, ROUND_HALF_UP);
    }

    public String getMessageContent(BigDecimal amount) {
        BigDecimal decimal = amount.setScale(2, ROUND_HALF_UP);
        String directionString = amount.compareTo(ZERO) > 0 ? "买入" : "卖出";
        return String.format("根据您设置的条件单，基金代码: %s, 基金名称: %s, 定投周期: %s, 您应该%s%f元", fundCode, fundName,
                firstCategoryOfCycle.getDescription() + " " + secondCategoryOfCycle.getDescription(),
                directionString, decimal);
    }

    /**
     * 根据目标市值和当前市值计算买卖金额
     * @param currentAmount 当前市值
     * @return 返回需要买入或者卖出的金额，负为卖出，正为买入
     */
    public BigDecimal calculateAmountWhenIsMaintain(BigDecimal currentAmount) {
        BigDecimal sub = currentAmount.subtract(targetMarketValue);
        BigDecimal rate = sub.divide(targetMarketValue, ROUND_HALF_UP);
        int topResult = rate.compareTo(positiveIncrease);
        int bottomResult = rate.compareTo(negativeIncrease);
        if (topResult <= 0 && bottomResult > -1) {
            return null;
        }
        return ZERO.subtract(sub);
    }

    public boolean isDubbleWeek() {
        return firstCategoryOfCycle == FixedInvestmentCycleFirstCategory.DOUBLE_WEEK;
    }
}