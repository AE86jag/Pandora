package com.pandora.infrastructure.wx;

import com.google.gson.Gson;
import com.pandora.infrastructure.util.HttpUtil;
import com.pandora.infrastructure.wx.model.WxLoginResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pandora.wx")
public class WxClient {

    @Autowired
    private Gson gson;

    @Setter
    private String url;

    @Setter
    private String appId;

    @Setter
    private String appSecret;

    public WxLoginResponse login(String code) {
        String uri = url +
                String.format("/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                appId, appSecret, code);
        String res  = HttpUtil.get(uri, String.class);
        WxLoginResponse response = gson.fromJson(res, WxLoginResponse.class);
        if (response == null || !response.isSuccess()) {
            throw new RuntimeException("微信登录失败");
        }
        return response;
    }

}
