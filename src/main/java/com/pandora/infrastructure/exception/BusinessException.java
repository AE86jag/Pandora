package com.pandora.infrastructure.exception;

public class BusinessException extends RuntimeException {
    private Boolean isPayAttentionTo;

    public BusinessException(String message, boolean isPayAttentionTo) {
        super(message);
        this.isPayAttentionTo = isPayAttentionTo;
    }

    public Boolean getIsPayAttentionTo() {
        return isPayAttentionTo;
    }
}
