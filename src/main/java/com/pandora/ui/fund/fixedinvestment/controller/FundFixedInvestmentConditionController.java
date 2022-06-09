package com.pandora.ui.fund.fixedinvestment.controller;

import com.pandora.domain.fund.fixedinvestment.model.FundFixedInvestmentCondition;
import com.pandora.domain.fund.fixedinvestment.service.IFundFixedInvestmentConditionService;
import com.pandora.domain.user.model.Role;
import com.pandora.infrastructure.interceptor.HasAnyRole;
import com.pandora.ui.fund.fixedinvestment.FundFixedInvestmentConditionVO;
import com.pandora.ui.fund.fixedinvestment.command.CreateFundFixedInvestmentConditionCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fund-fixed-investment-condition")
public class FundFixedInvestmentConditionController {

    @Autowired
    private IFundFixedInvestmentConditionService iFundFixedInvestmentConditionService;

    @PostMapping
    @HasAnyRole(Role.NORMAL)
    public void create(@RequestBody CreateFundFixedInvestmentConditionCommand command) {
        command.check();
        iFundFixedInvestmentConditionService.createFundFixedInvestmentCondition(command.to());
    }

    @GetMapping
    @HasAnyRole(Role.NORMAL)
    public List<FundFixedInvestmentConditionVO> getFundFixedInvestmentConditions(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        List<FundFixedInvestmentCondition> conditions =
                iFundFixedInvestmentConditionService.getUserConditionsPage(page, size);
        return conditions.stream().map(FundFixedInvestmentConditionVO::from).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @HasAnyRole(Role.NORMAL)
    public void suspendCondition(@PathVariable String id, Integer status) {
        iFundFixedInvestmentConditionService.changeStatusById(id, status);

    }

    @PostMapping("/notify")
    public void messageNotify() {
        iFundFixedInvestmentConditionService.messageNotify();
    }
}
