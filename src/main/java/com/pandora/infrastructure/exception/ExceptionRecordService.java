package com.pandora.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExceptionRecordService {

    @Autowired
    private ExceptionMapper exceptionMapper;

    public void save(Exception e) {
        try {
            exceptionMapper.insert(ExceptionRecord.from(e, null, true));
        } catch (Exception ep) {
            log.error("save exception record error", ep);
        }

    }
}

