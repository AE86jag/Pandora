package com.pandora.domain.fund.product.mapper;


import com.pandora.domain.fund.product.model.Nav;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

@Mapper
public interface NavMapper {

    Integer insert(Nav nav);

    Integer saveOrUpdate(Nav nav);

    Integer findCountByFundCodeAndDate(@Param("fundCode")String fundCode, @Param("date")LocalDate date);
}