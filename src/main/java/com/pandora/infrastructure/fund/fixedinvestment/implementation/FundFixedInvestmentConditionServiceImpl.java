package com.pandora.infrastructure.fund.fixedinvestment.implementation;

import com.pandora.domain.fund.fixedinvestment.mapper.FundFixedInvestmentConditionMapper;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import com.pandora.domain.fund.fixedinvestment.service.IFundFixedInvestmentConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundFixedInvestmentConditionServiceImpl implements IFundFixedInvestmentConditionService {

    @Autowired
    private FundFixedInvestmentConditionMapper fundFixedInvestmentConditionMapper;

    @Override
    public void createFundFixedInvestmentCondition(FundFixedInvestmentCondition fundFixedInvestmentCondition) {
        //参数校验
        //检测二级分类是否符合条件
        fundFixedInvestmentConditionMapper.insert(fundFixedInvestmentCondition);
    }
}
