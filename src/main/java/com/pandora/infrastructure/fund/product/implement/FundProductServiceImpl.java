package com.pandora.infrastructure.fund.product.implement;

import com.pandora.domain.fund.product.model.FundSearchResult;
import com.pandora.domain.fund.product.service.IFundProductService;
import com.pandora.infrastructure.eastmoney.EastMoneyClient;
import com.pandora.infrastructure.eastmoney.FundSearchResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FundProductServiceImpl implements IFundProductService {

    @Autowired
    private EastMoneyClient eastMoneyClient;

    @Override
    public List<FundSearchResult> searchByKey(String key) {
        List<FundSearchResultDTO> response = eastMoneyClient.searchByKey(key);
        return response.stream().filter(FundSearchResultDTO::isFund)
                .map(FundSearchResultDTO::to).collect(Collectors.toList());
    }
}
