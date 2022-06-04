package com.pandora.ui.fund.product.controller;

import com.pandora.domain.fund.product.model.FundSearchResult;
import com.pandora.domain.fund.product.service.IFundProductService;
import com.pandora.ui.fund.product.vo.FundSearchResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fund-product")
public class FundProductController {

    @Autowired
    private IFundProductService iFundProductService;

    @GetMapping("/search")
    public List<FundSearchResultVO> search(String key) {
        List<FundSearchResult> results = iFundProductService.searchByKey(key);
        return results.stream().map(FundSearchResultVO::from).collect(Collectors.toList());
    }
}
