package com.pandora.ui.demand.controller;

import com.pandora.domain.demand.model.DemandRegister;
import com.pandora.domain.demand.service.IDemandService;
import com.pandora.domain.user.model.Role;
import com.pandora.infrastructure.interceptor.HasAnyRole;
import com.pandora.ui.demand.command.CreateDemandCommand;
import com.pandora.ui.demand.vo.DemandRegisterListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/demand")
public class DemandController {

    @Autowired
    private IDemandService iDemandService;


    @PostMapping
    @HasAnyRole(Role.NORMAL)
    public void createDemand(@RequestBody CreateDemandCommand command) {
        System.out.println("Controller的线程ID: " + Thread.currentThread().getId());
        command.check();
        iDemandService.demandRegister(command.getName(), command.getContact(), command.getDemandDetail());
    }

    @GetMapping
    @HasAnyRole(Role.NORMAL)
    public List<DemandRegisterListVO> demandRegisterLists(@RequestParam(required = false, defaultValue = "0") int page,
                                                          @RequestParam(required = false, defaultValue = "10") int size) {
        List<DemandRegister> registers = iDemandService.getDemandRegisterList(page, size);
        return registers.stream().map(DemandRegisterListVO::from).collect(Collectors.toList());
    }
}
