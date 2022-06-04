package com.pandora.domain.fund.product.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FundSearchResult {

    private String fundCode;

    private String fundName;

    private Date navDate;

    private BigDecimal nav;
}
