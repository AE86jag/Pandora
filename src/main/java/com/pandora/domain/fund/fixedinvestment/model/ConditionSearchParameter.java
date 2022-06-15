package com.pandora.domain.fund.fixedinvestment.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConditionSearchParameter {

    private FixedInvestmentCycleFirstCategory firstCategoryOfDay;
    private FixedInvestmentCycleSecondCategory secondCategoryOfDay;
    private FixedInvestmentCycleFirstCategory firstCategoryOfWeek;
    private FixedInvestmentCycleSecondCategory secondCategoryOfWeek;
    private FixedInvestmentCycleFirstCategory firstCategoryOfDoubleWeek;
    private FixedInvestmentCycleSecondCategory secondCategoryOfDoubleWeek;
    private FixedInvestmentCycleFirstCategory firstCategoryOfMonth;
    private FixedInvestmentCycleSecondCategory secondCategoryOfMonth;

    public static ConditionSearchParameter from(LocalDate now) {
        ConditionSearchParameter parameter = new ConditionSearchParameter();
        parameter.setFirstCategoryOfDay(FixedInvestmentCycleFirstCategory.DAY);
        parameter.setSecondCategoryOfDay(FixedInvestmentCycleSecondCategory.DAY);

        String dayOfWeek = now.getDayOfWeek().toString();
        parameter.setFirstCategoryOfWeek(FixedInvestmentCycleFirstCategory.WEEK);
        parameter.setSecondCategoryOfWeek(FixedInvestmentCycleSecondCategory.valueOf(dayOfWeek));

        parameter.setFirstCategoryOfDoubleWeek(FixedInvestmentCycleFirstCategory.DOUBLE_WEEK);
        parameter.setSecondCategoryOfDoubleWeek(FixedInvestmentCycleSecondCategory.valueOf("DOUBLE_" + dayOfWeek));

        int dayOfMonth = now.getDayOfMonth();
        parameter.setFirstCategoryOfMonth(FixedInvestmentCycleFirstCategory.MONTH);
        parameter.setSecondCategoryOfMonth(FixedInvestmentCycleSecondCategory.from(dayOfMonth));
        return parameter;
    }


}
