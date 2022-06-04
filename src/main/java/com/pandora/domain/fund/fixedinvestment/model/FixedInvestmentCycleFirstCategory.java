package com.pandora.domain.fund.fixedinvestment.model;

public enum FixedInvestmentCycleFirstCategory {

    DAY("按交易日"),
    WEEK("每周定投"),
    DOUBLE_WEEK("双周定投"),
    MONTH("每月定投");
    private String description;

    public String getDescription() {
        return description;
    }

    FixedInvestmentCycleFirstCategory(String description) {
        this.description = description;
    }
}
