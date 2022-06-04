package com.pandora.ui.convertiblebond.controller;

import com.pandora.domain.convertiblebond.model.ConvertibleBondSearchResult;
import com.pandora.domain.convertiblebond.model.ConvertibleBondShotRegister;
import com.pandora.domain.convertiblebond.service.IConvertibleBondService;
import com.pandora.domain.user.model.Role;
import com.pandora.infrastructure.interceptor.HasAnyRole;
import com.pandora.ui.convertiblebond.command.CreateConvertibleBondShotRegisterCommand;
import com.pandora.ui.convertiblebond.vo.ConvertibleBondSearchResultVO;
import com.pandora.ui.convertiblebond.vo.ConvertibleBondShotListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/convertible-bond")
public class ConvertibleBondController {

    @Autowired
    private IConvertibleBondService convertibleBondService;

    @GetMapping
    public String test() {
        return "1";
    }

    @PutMapping
    public void syncConvertibleBond() {
        convertibleBondService.syncConvertibleBonds();
    }

    @GetMapping("/search")
    public List<ConvertibleBondSearchResultVO> searchByKey(String key) {
        List<ConvertibleBondSearchResult> results = convertibleBondService.searchByKey(key);
        return results.stream().map(ConvertibleBondSearchResultVO::from).collect(Collectors.toList());
    }

    @PostMapping("/shot-register")
    @HasAnyRole(Role.NORMAL)
    public void createConvertibleBondShotRegister(@RequestBody CreateConvertibleBondShotRegisterCommand command) {
        command.check();
        convertibleBondService.shotRegister(command.getBondCode(), command.getEmail());
    }

    @GetMapping("/shot-register")
    @HasAnyRole(Role.NORMAL)
    public List<ConvertibleBondShotListVO> convertibleBondShotRegisterList(@RequestParam(required = false, defaultValue = "0") int page,
                                                                           @RequestParam(required = false, defaultValue = "10") int size) {
        List<ConvertibleBondShotRegister> registers = convertibleBondService.convertibleBondShotRegisterList(page, size);
        return registers.stream().map(ConvertibleBondShotListVO::from).collect(Collectors.toList());
    }
}
