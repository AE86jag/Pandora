package com.pandora.domain.holiday.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TradeDay {

    private LocalDate date;

    private Boolean isWorkDay;

    private LocalDateTime createdTime;

    private LocalDateTime lastModifiedTime;
}
