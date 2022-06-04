package com.pandora.ui.fund.fixedinvestment.command;

import com.pandora.domain.fund.fixedinvestment.model.FixedInvestmentCycleFirstCategory;
import com.pandora.domain.fund.fixedinvestment.model.FixedInvestmentCycleSecondCategory;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.util.CommonUtil;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CreateFundFixedInvestmentConditionCommand {
    private String fundCode;
    private String fundName;
    private BigDecimal targetMarketValue;
    private FixedInvestmentCycleFirstCategory firstCategory;
    private FixedInvestmentCycleSecondCategory secondCategory;
    private BigDecimal positiveIncrease;
    private BigDecimal negativeIncrease;
    private BigDecimal perAmount;
    private String email;

    public void check() {
        if (StringUtils.isEmpty(fundCode)) {
            throw new RuntimeException("请输入基金代码");
        }
        if (StringUtils.isEmpty(fundName)) {
            throw new RuntimeException("请输入基金名称");
        }
        if (StringUtils.isEmpty(targetMarketValue)) {
            throw new RuntimeException("请输入目标市值");
        }
        if (StringUtils.isEmpty(firstCategory)) {
            throw new RuntimeException("请输入定投周期");
        }if (StringUtils.isEmpty(secondCategory)) {
            throw new RuntimeException("请输入定投时间");
        }
        if (StringUtils.isEmpty(positiveIncrease)) {
            throw new RuntimeException("请输入正波动幅度");
        }
        if (StringUtils.isEmpty(negativeIncrease)) {
            throw new RuntimeException("请输入负波动幅度");
        }
        if (StringUtils.isEmpty(perAmount)) {
            throw new RuntimeException("请输入每笔委托");
        }
        if (StringUtils.isEmpty(email)) {
            throw new RuntimeException("请输入邮箱");
        }
        if (!secondCategory.checkCategory(firstCategory)) {
            throw new RuntimeException("定投周期输入有误");
        }
    }

    public FundFixedInvestmentCondition to() {
        FundFixedInvestmentCondition condition = new FundFixedInvestmentCondition();
        condition.setId(CommonUtil.generateId());
        condition.setFundCode(fundCode);
        condition.setFundName(fundName.trim());
        condition.setTargetMarketValue(targetMarketValue);
        condition.setFirstCategoryOfCycle(firstCategory);
        condition.setSecondCategoryOfCycle(secondCategory);
        condition.setPositiveIncrease(positiveIncrease);
        condition.setNegativeIncrease(negativeIncrease);
        condition.setPerAmount(perAmount);
        condition.setEmail(email);
        condition.setStatus(1);
        condition.setUserId(CurrentUserUtils.currenUserId());
        return condition;
    }
}
