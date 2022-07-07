package com.pandora.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExceptionRecordService {

    @Autowired
    private ExceptionMapper exceptionMapper;

    @Async
    public void save(Exception e) {
        exceptionMapper.insert(ExceptionRecord.from(e.getClass().getName(), e.getMessage(),
                e.getStackTrace(), null, true));
    }

    @Async
    public void save(String className, String fundCode, String fundName, String url, boolean isAttention) {
        String message = String.format("%s(%s)最近两个交易日的净值", fundName, fundCode);
        ExceptionRecord exceptionRecord = ExceptionRecord.from(className, message, null, url, isAttention);
        exceptionMapper.insert(exceptionRecord);
    }
}

