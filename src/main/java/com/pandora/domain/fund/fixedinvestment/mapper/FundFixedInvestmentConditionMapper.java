package com.pandora.domain.fund.fixedinvestment.mapper;


import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FundFixedInvestmentConditionMapper {
    int insert(FundFixedInvestmentCondition condition);
}