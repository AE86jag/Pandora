package com.pandora.domain.fund.fixedinvestment.model;

import static com.pandora.domain.fund.fixedinvestment.model.FixedInvestmentCycleFirstCategory.*;

public enum FixedInvestmentCycleSecondCategory {
    DAY("每个交易日", FixedInvestmentCycleFirstCategory.DAY),
    MONDAY("每周周一", WEEK),
    TUESDAY("每周周二", WEEK),
    WEDNESDAY("每周周三", WEEK),
    THURSDAY("每周周四", WEEK),
    FRIDAY("每周周五", WEEK),
    DOUBLE_MONDAY("双周周一", DOUBLE_WEEK),
    DOUBLE_TUESDAY("双周周二", DOUBLE_WEEK),
    DOUBLE_WEDNESDAY("双周周三", DOUBLE_WEEK),
    DOUBLE_THURSDAY("双周周四", DOUBLE_WEEK),
    DOUBLE_FRIDAY("双周周五", DOUBLE_WEEK),
    ONE("每月1号", MONTH),
    TWO("每月2号", MONTH),
    THREE("每月3号", MONTH),
    FOUR("每月4号", MONTH),
    FIVE("每月5号", MONTH),
    SIX("每月6号", MONTH),
    SEVEN("每月7号", MONTH),
    EIGHT("每月8号", MONTH),
    NINE("每月9号", MONTH),
    TEN("每月10号", MONTH),
    ELEVEN("每月11号", MONTH),
    TWELVE("每月12号", MONTH),
    THIRTEEN("每月13号", MONTH),
    FOURTEEN("每月14号", MONTH),
    FIFTEEN("每月15号", MONTH),
    SIXTEEN("每月16号", MONTH),
    SEVENTEEN("每月17号", MONTH),
    EIGHTEEN("每月18号", MONTH),
    NINETEEN("每月19号", MONTH),
    TWENTY("每月20号", MONTH),
    TWENTY_ONE("每月21号", MONTH),
    TWENTY_TWO("每月22号", MONTH),
    TWENTY_THREE("每月23号", MONTH),
    TWENTY_FOUR("每月24号", MONTH),
    TWENTY_FIVE("每月25号", MONTH),
    TWENTY_SIX("每月26号", MONTH),
    TWENTY_SEVEN("每月27号", MONTH),
    TWENTY_EIGHT("每月28号", MONTH);

    private String description;
    private FixedInvestmentCycleFirstCategory parentCategory;

    public FixedInvestmentCycleFirstCategory getParentCategory() {
        return parentCategory;
    }

    public String getDescription() {
        return description;
    }

    FixedInvestmentCycleSecondCategory(String description, FixedInvestmentCycleFirstCategory parentCategory) {
        this.description = description;
        this.parentCategory = parentCategory;
    }

    public boolean checkCategory(FixedInvestmentCycleFirstCategory firstCategory) {
        return this.getParentCategory() == firstCategory;
    }


}
