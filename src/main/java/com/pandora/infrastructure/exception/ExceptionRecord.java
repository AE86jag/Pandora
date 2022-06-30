package com.pandora.infrastructure.exception;

import com.pandora.infrastructure.common.CurrentUserUtils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionRecord {

    private Integer id;

    private String clazz;

    private String message;

    private String stack;

    private String url;

    private String userId;

    private Boolean isAttention;

    private LocalDateTime lastModifiedTime;

    private LocalDateTime createdTime;

    public static ExceptionRecord from(Exception e, String url) {
        ExceptionRecord record = new ExceptionRecord();

        String clazz = e.getClass().getName();
        record.setClazz(clazz);

        String message = e.getMessage();
        record.setMessage(message);

        Boolean isAttention = e instanceof BusinessException && ((BusinessException) e).getIsPayAttentionTo();
        record.setIsAttention(isAttention);

        StringBuilder builder = new StringBuilder();
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 0; i < stackTrace.length && i < 7; i++) {
            String stack = stackTrace[i].toString();
            if (stack.startsWith("com.pandora")) {
                builder.append(stack).append("\\n ");
            }
        }
        String stack = builder.toString();
        record.setStack(stack);

        String userId = CurrentUserUtils.currentUserId();
        record.setUserId(userId);

        record.setUrl(url);

        return record;
    }
}
