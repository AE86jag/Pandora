package com.pandora.infrastructure.convertiblebond.model;

import lombok.Data;

import java.util.List;

@Data
public class EastMoneyBondDTO<T> {

    private String version;

    private boolean success;

    private String message;

    private Result<T> result;

    private int code;

    @Data
    public static class Result<T> {
        private int pages;

        private List<T> data;

        private int count;
    }

    public boolean isSuccess() {
        return success && code == 0;
    }
}
