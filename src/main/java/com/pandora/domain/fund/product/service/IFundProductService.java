package com.pandora.domain.fund.product.service;

import com.pandora.domain.fund.product.model.FundSearchResult;

import java.util.List;

public interface IFundProductService {

    List<FundSearchResult> searchByKey(String key);
}
