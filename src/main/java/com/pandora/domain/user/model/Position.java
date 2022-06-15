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
    private BigDecimal share;
    private LocalDateTime lastModifiedTime;
    private LocalDateTime createdTime;

    public BigDecimal calculationAmount(BigDecimal nav) {
        return share.multiply(nav);
    }
}