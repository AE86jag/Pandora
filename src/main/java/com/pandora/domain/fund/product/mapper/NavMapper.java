package com.pandora.domain.fund.product.mapper;


import com.pandora.domain.fund.product.model.Nav;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NavMapper {

    Integer insert(Nav nav);
}