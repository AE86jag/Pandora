package com.pandora.ui.fund.product.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pandora.domain.fund.product.model.FundSearchResult;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FundSearchResultVO {

    private String fundCode;

    private String fundName;

    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date navDate;

    private BigDecimal nav;

    public static FundSearchResultVO from(FundSearchResult result) {
        FundSearchResultVO vo = new FundSearchResultVO();
        vo.setFundCode(result.getFundCode());
        vo.setFundName(result.getFundName());
        vo.setNav(result.getNav());
        vo.setNavDate(result.getNavDate());
        return vo;
    }
}
