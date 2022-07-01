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
        //TODO 异常处理完，前端不会抛异常; 异常信息不应该过滤非框架的堆栈
        ExceptionRecord record = ExceptionRecord.from(e, request.getRequestURL().toString());
        exceptionMapper.insert(record);
        throw e;
    }
}
