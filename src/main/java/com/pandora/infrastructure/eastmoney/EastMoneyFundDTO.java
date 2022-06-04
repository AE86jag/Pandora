package com.pandora.infrastructure.eastmoney;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class EastMoneyFundDTO<T> {

    @SerializedName("ErrCode")
    private String errorCode;

    @SerializedName("ErrMsg")
    private String errorMessage;

    @SerializedName("Datas")
    private T data;

    public boolean isSuccess() {
        return "0".equals(errorCode);
    }
}
