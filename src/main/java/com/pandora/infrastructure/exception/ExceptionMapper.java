package com.pandora.infrastructure.exception;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExceptionMapper {

    Integer insert(ExceptionRecord record);
}
