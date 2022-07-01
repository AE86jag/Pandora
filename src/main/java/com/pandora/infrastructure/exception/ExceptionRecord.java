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
        record.setClazz(clazz.length() > 100 ? clazz.substring(0, 100) : clazz);

        String message = e.getMessage();
        record.setMessage(message.length() > 500 ? message.substring(0, 500) : message);

        Boolean isAttention = e instanceof BusinessException && ((BusinessException) e).getIsPayAttentionTo();
        record.setIsAttention(isAttention);

        StringBuilder builder = new StringBuilder();
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 0; i < stackTrace.length && i < 8; i++) {
            String stack = stackTrace[i].toString();
            if (i < 4 || stack.startsWith("com.pandora")) {
                builder.append(stack).append("\\n ");
            }
        }
        String stack = builder.toString();
        record.setStack(stack.length() > 1000 ? stack.substring(0, 1000) : stack);

        String userId = CurrentUserUtils.currentUserId();
        record.setUserId(userId);

        record.setUrl(url);

        return record;
    }
}
