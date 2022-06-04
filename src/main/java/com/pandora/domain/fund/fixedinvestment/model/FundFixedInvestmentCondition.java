package com.pandora.domain.fund.fixedinvestment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private LocalDateTime createdTime;
    private LocalDateTime lastModifiedTime;


    public String getCycleDescription() {
        return firstCategoryOfCycle.getDescription() + secondCategoryOfCycle.getDescription();
    }
}