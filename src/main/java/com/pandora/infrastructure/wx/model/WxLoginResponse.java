package com.pandora.infrastructure.wx.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class WxLoginResponse {

    @SerializedName("openid")
    private String openId;

    @SerializedName("session_key")
    private String sessionKey;

    @SerializedName("unionid")
    private String unionId;

    public boolean isSuccess() {
        return !StringUtils.isEmpty(openId);
    }
}
