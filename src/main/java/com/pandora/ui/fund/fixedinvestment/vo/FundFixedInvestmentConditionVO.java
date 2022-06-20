package com.pandora.ui.fund.fixedinvestment.vo;

import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundFixedInvestmentConditionVO {
    private String id;

    private String fundCode;

    private String fundName;

    private String cycle;

    private BigDecimal targetMarketValue;

    private BigDecimal positiveIncrease;

    private BigDecimal negativeIncrease;

    private BigDecimal perAmount;

    private Integer status;

    private String email;

    public static FundFixedInvestmentConditionVO from(FundFixedInvestmentCondition condition) {
        FundFixedInvestmentConditionVO vo = new FundFixedInvestmentConditionVO();
        vo.setId(condition.getId());
        vo.setFundCode(condition.getFundCode());
        vo.setFundName(condition.getFundName());
        vo.setCycle(condition.getCycleDescription());
        vo.setTargetMarketValue(condition.getTargetMarketValue());
        vo.setPositiveIncrease(condition.getPositiveIncrease());
        vo.setNegativeIncrease(condition.getNegativeIncrease());
        vo.setPerAmount(condition.getPerAmount());
        vo.setStatus(condition.getStatus());
        vo.setEmail(condition.getEmail());
        return vo;
    }
}
