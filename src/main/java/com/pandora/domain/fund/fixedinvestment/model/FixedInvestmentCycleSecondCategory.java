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
    ONE("每月1号", MONTH, 1),
    TWO("每月2号", MONTH, 2),
    THREE("每月3号", MONTH, 3),
    FOUR("每月4号", MONTH, 3),
    FIVE("每月5号", MONTH, 5),
    SIX("每月6号", MONTH, 6),
    SEVEN("每月7号", MONTH, 7),
    EIGHT("每月8号", MONTH, 8),
    NINE("每月9号", MONTH, 9),
    TEN("每月10号", MONTH, 10),
    ELEVEN("每月11号", MONTH, 11),
    TWELVE("每月12号", MONTH, 12),
    THIRTEEN("每月13号", MONTH,13),
    FOURTEEN("每月14号", MONTH,14),
    FIFTEEN("每月15号", MONTH, 15),
    SIXTEEN("每月16号", MONTH,16),
    SEVENTEEN("每月17号", MONTH, 17),
    EIGHTEEN("每月18号", MONTH, 18),
    NINETEEN("每月19号", MONTH, 19),
    TWENTY("每月20号", MONTH, 20),
    TWENTY_ONE("每月21号", MONTH, 21),
    TWENTY_TWO("每月22号", MONTH, 22),
    TWENTY_THREE("每月23号", MONTH,23),
    TWENTY_FOUR("每月24号", MONTH, 24),
    TWENTY_FIVE("每月25号", MONTH,25),
    TWENTY_SIX("每月26号", MONTH,26),
    TWENTY_SEVEN("每月27号", MONTH, 27),
    TWENTY_EIGHT("每月28号", MONTH, 28);

    private String description;
    private FixedInvestmentCycleFirstCategory parentCategory;
    private Integer monthIndex;

    public FixedInvestmentCycleFirstCategory getParentCategory() {
        return parentCategory;
    }

    public String getDescription() {
        return description;
    }

    public Integer getMonthIndex() {
        return monthIndex;
    }

    FixedInvestmentCycleSecondCategory(String description, FixedInvestmentCycleFirstCategory parentCategory) {
        this.description = description;
        this.parentCategory = parentCategory;
    }

    FixedInvestmentCycleSecondCategory(String description, FixedInvestmentCycleFirstCategory parentCategory, Integer monthIndex) {
        this.description = description;
        this.parentCategory = parentCategory;
        this.monthIndex = monthIndex;
    }

    public static FixedInvestmentCycleSecondCategory from(Integer monthIndex) {
        for (FixedInvestmentCycleSecondCategory value : FixedInvestmentCycleSecondCategory.values()) {
            Integer monthIndexValue = value.getMonthIndex();
            if (monthIndexValue != null && monthIndexValue.equals(monthIndex)) {
                return value;
            }
        }
        return null;
    }

    public boolean checkCategory(FixedInvestmentCycleFirstCategory firstCategory) {
        return this.getParentCategory() == firstCategory;
    }


}
