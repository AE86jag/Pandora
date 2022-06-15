package com.pandora.domain.holiday.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TradeDayMapper {

    Boolean isWorkDay(@Param("date") LocalDate date);

    LocalDate findNextTradeDay(@Param("date")LocalDate date);

    LocalDate findPreTradeDay(@Param("date")LocalDate date);

    List<LocalDate> findTwoAheadTradeDay(@Param("date")LocalDate date);
}
