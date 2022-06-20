package com.pandora.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Position {
    private String userId;
    private String fundCode;
    private String fundName;
    private BigDecimal share;
    private LocalDateTime lastModifiedTime;
    private LocalDateTime createdTime;

    public BigDecimal calculationAmount(BigDecimal nav) {
        return share.multiply(nav);
    }

    public static Position build(String userId, String fundCode, String fundName, BigDecimal share) {
        Position position = new Position();
        position.setUserId(userId);
        position.setFundCode(fundCode);
        position.setFundName(fundName);
        position.setShare(share);
        return position;
    }
}