package com.pandora.infrastructure.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice("com.pandora.ui")
public class MyExceptionHandler {

    @Autowired
    private ExceptionMapper exceptionMapper;

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e, HttpServletRequest request){
        ExceptionRecord record = ExceptionRecord.from(e, request.getRequestURL().toString());
        exceptionMapper.insert(record);
    }
}
