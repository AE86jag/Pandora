package com.pandora.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice("com.pandora.ui")
@Slf4j
public class MyExceptionHandler {

    @Autowired
    private ExceptionMapper exceptionMapper;

    @ExceptionHandler(RuntimeException.class)
    public void exceptionHandler(RuntimeException e, HttpServletRequest request){
        Boolean isAttention = e instanceof BusinessException && ((BusinessException) e).getIsPayAttentionTo();
        ExceptionRecord record = ExceptionRecord.from(e.getClass().getName(), e.getMessage(), e.getStackTrace(),
                request.getRequestURL().toString(), isAttention);
        exceptionMapper.insert(record);
        throw e;
    }
}
