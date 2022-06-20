package com.pandora.domain.fund.fixedinvestment.model;

import com.pandora.infrastructure.common.CurrentUserUtils;
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
    private String userId;

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

        parameter.setUserId(CurrentUserUtils.currentUserId());
        return parameter;
    }


}
