package com.pandora.infrastructure.eastmoney;

import com.google.gson.annotations.SerializedName;
import com.pandora.domain.fund.product.model.Nav;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
public class NavEstimateDTO {

    @SerializedName("fundcode")
    private String fundCode;

    @SerializedName("name")
    private String fundName;

    /*净值日期*/
    @SerializedName("jzrq")
    private Date navDate;

    /*单位净值*/
    @SerializedName("dwjz")
    private BigDecimal unitNav;

    /*估算净值*/
    @SerializedName("gsz")
    private BigDecimal estimateNav;

    /*净值估算涨幅*/
    @SerializedName("gszzl")
    private BigDecimal estimateIncreaseRate;

    /*估算时间*/
    @SerializedName("gztime")
    private Date estimateDate;

    public LocalDate getNavLocalDate() {
        Instant instant = navDate.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate();
    }
}
