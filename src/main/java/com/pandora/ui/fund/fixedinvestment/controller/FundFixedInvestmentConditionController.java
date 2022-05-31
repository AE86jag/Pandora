package com.pandora.ui.fund.fixedinvestment.controller;

import com.pandora.domain.fund.fixedinvestment.service.IFundFixedInvestmentConditionService;
import com.pandora.ui.fund.fixedinvestment.command.CreateFundFixedInvestmentConditionCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fund-fixed-investment-condition")
public class FundFixedInvestmentConditionController {

    @Autowired
    private IFundFixedInvestmentConditionService iFundFixedInvestmentConditionService;

    @PostMapping
    public void create(@RequestBody CreateFundFixedInvestmentConditionCommand command) {
        command.check();
        iFundFixedInvestmentConditionService.createFundFixedInvestmentCondition(command.to());
    }
}
