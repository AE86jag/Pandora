package com.pandora.domain.convertiblebond.model;

import com.pandora.infrastructure.convertiblebond.model.EastMoneyConvertibleBond;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ConvertibleBond {

    /**
     * 债券代码
     */
    private String bondCode;

    /**
     * 债券简称
     */
    private String bondShortName;

    /**
     * 申购日期
     */
    private Date subscriptionDate;

    /**
     * 申购代码
     */
    private String subscriptionCode;

    /**
     * 正股代码
     */
    private String stockCode;

    /**
     * 正股简称
     */
    private String stockShortName;

    /**
     * 转股价
     */
    private BigDecimal transferStockPrice;

    /**
     * 债现值
     */
    private BigDecimal currentBondPrice;

    /**
     * 发行规模，单位亿元
     */
    private BigDecimal issueScale;

    /**
     * 中签号发布日
     */
    private Date shotResultPublishDate;

    /**
     * 中签率
     */
    private BigDecimal shotRate;

    /**
     * 上市时间
     */
    private Date listedDate;

    /**
     * 最后修改时间
     */
    private LocalDateTime lastModifyTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public void setTransferStockPrice(BigDecimal transferStockPrice) {
        if (transferStockPrice != null) {
            this.transferStockPrice = transferStockPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public void setCurrentBondPrice(BigDecimal currentBondPrice) {
        if (currentBondPrice != null) {
            this.currentBondPrice = currentBondPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public void setIssueScale(BigDecimal issueScale) {
        if (issueScale != null) {
            this.issueScale = issueScale.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    public void setShotRate(BigDecimal shotRate) {
        if (shotRate != null) {
            this.shotRate = shotRate.setScale(6, BigDecimal.ROUND_HALF_UP);
        }
    }

    public static ConvertibleBond from(EastMoneyConvertibleBond eastMoneyConvertibleBond) {
        ConvertibleBond convertibleBondPO = new ConvertibleBond();
        convertibleBondPO.setBondCode(eastMoneyConvertibleBond.getBondCode());
        convertibleBondPO.setBondShortName(eastMoneyConvertibleBond.getBondShortName());
        convertibleBondPO.setSubscriptionDate(eastMoneyConvertibleBond.getSubscriptionDate());
        convertibleBondPO.setSubscriptionCode(eastMoneyConvertibleBond.getSubscriptionCode());
        convertibleBondPO.setStockCode(eastMoneyConvertibleBond.getStockCode());
        convertibleBondPO.setStockShortName(eastMoneyConvertibleBond.getStockShortName());
        convertibleBondPO.setTransferStockPrice(eastMoneyConvertibleBond.getTransferStockPrice());
        convertibleBondPO.setCurrentBondPrice(eastMoneyConvertibleBond.getCurrentBondPrice());
        convertibleBondPO.setIssueScale(eastMoneyConvertibleBond.getIssueScale());
        convertibleBondPO.setShotResultPublishDate(eastMoneyConvertibleBond.getShotResultPublishDate());
        convertibleBondPO.setShotRate(eastMoneyConvertibleBond.getShotRate());
        convertibleBondPO.setListedDate(eastMoneyConvertibleBond.getListedDate());
        return convertibleBondPO;
    }

    public ConvertibleBondSearchResult to() {
        ConvertibleBondSearchResult result = new ConvertibleBondSearchResult();
        result.setBondCode(bondCode);
        result.setBondShortName(bondShortName);
        return result;
    }
}
