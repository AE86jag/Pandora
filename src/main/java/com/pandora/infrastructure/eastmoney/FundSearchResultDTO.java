package com.pandora.infrastructure.eastmoney;

import com.google.gson.annotations.SerializedName;
import com.pandora.domain.fund.product.model.FundSearchResult;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FundSearchResultDTO {

    @SerializedName("CATEGORY")
    private int category;

    @SerializedName("CATEGORYDESC")
    private String categoryDescription;

    @SerializedName("CODE")
    private String fundCode;

    @SerializedName("NAME")
    private String fundName;

    @SerializedName("FundBaseInfo")
    private FundBaseInfo fundBaseInfo;


    public boolean isFund() {
        return 700 == category;
    }


    public FundSearchResult to() {
        FundSearchResult result = new FundSearchResult();
        result.setFundCode(fundCode);
        result.setFundName(fundName);
        result.setNav(fundBaseInfo == null ? null : fundBaseInfo.getNav());
        result.setNavDate(fundBaseInfo == null ? null : fundBaseInfo.getNavDate());
        return result;
    }

    @Data
    private static class FundBaseInfo {

        @SerializedName("FSRQ")
        private Date navDate;

        @SerializedName("DWJZ")
        private BigDecimal nav;


        @SerializedName("FTYPE")
        private String fundType;

        @SerializedName("JJJL")
        private String manager;

        @SerializedName("SHORTNAME")
        private String shortName;

    }
}
