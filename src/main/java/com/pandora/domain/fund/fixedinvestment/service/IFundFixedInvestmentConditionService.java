package com.pandora.domain.fund.fixedinvestment.service;

import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentConditionRecord;

import java.util.List;

public interface IFundFixedInvestmentConditionService {

    void createFundFixedInvestmentCondition(FundFixedInvestmentCondition fundFixedInvestmentCondition);

    List<FundFixedInvestmentCondition> getUserConditionsPage(int page, int size);

    void changeStatusById(String id, Integer status);

    void messageNotify();

    List<FundFixedInvestmentConditionRecord> findCurrentUserRecords(String fundCode);

    void liquidationExecution ();

    void conditionExecution();

    void postponeExecution();
}
