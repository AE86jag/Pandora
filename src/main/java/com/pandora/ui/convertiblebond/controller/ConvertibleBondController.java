package com.pandora.ui.convertiblebond.controller;

import com.pandora.domain.convertiblebond.model.ConvertibleBondSearchResult;
import com.pandora.domain.convertiblebond.service.IConvertibleBondService;
import com.pandora.ui.convertiblebond.command.ConvertibleBondSearchResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
