package com.pandora.infrastructure.convertiblebond.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EastMoneyConvertibleBond {

    /**
     * 债券代码
     */
    @SerializedName("SECURITY_CODE")
    private String bondCode;

    /**
     * 债券简称
     */
    @SerializedName("SECURITY_NAME_ABBR")
    private String bondShortName;

    /**
     * 申购日期
     */
    @SerializedName("PUBLIC_START_DATE")
    private Date subscriptionDate;

    /**
     * 申购代码
     */
    @SerializedName("CORRECODE")
    private String subscriptionCode;

    /**
     * 正股代码
     */
    @SerializedName("CONVERT_STOCK_CODE")
    private String stockCode;

    /**
     * 正股简称
     */
    @SerializedName("SECURITY_SHORT_NAME")
    private String stockShortName;

    /**
     * 转股价（使用初始转股价，上市公司可能向下修正转股价）
     */
    @SerializedName("INITIAL_TRANSFER_PRICE")
    private BigDecimal transferStockPrice;


    /**
     * 债现值
     */
    @SerializedName("CURRENT_BOND_PRICENEW")
    private BigDecimal currentBondPrice;

    /**
     * 发行规模，单位亿元
     */
    @SerializedName("ACTUAL_ISSUE_SCALE")
    private BigDecimal issueScale;

    /**
     * 中签号发布日
     */
    @SerializedName("BOND_START_DATE")
    private Date shotResultPublishDate;

    /**
     * 中签率
     */
    @SerializedName("ONLINE_GENERAL_LWR")
    private BigDecimal shotRate;

    /**
     * 上市时间
     */
    @SerializedName("LISTING_DATE")
    private Date listedDate;
}
