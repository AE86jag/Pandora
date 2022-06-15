package com.pandora.domain.fund.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Nav {
    private String fundCode;
    private LocalDate navDate;
    private BigDecimal nav;
    private LocalDateTime lastModifiedTime;
    private LocalDateTime createdTime;


    public static Nav build(String fundCode, LocalDate navDate, BigDecimal navValue) {
        Nav nav = new Nav();
        nav.setFundCode(fundCode);
        nav.setNavDate(navDate);
        nav.setNav(navValue);
        return nav;
    }
}