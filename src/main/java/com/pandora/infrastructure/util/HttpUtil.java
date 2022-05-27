package com.pandora.infrastructure.util;

import org.springframework.web.client.RestTemplate;

public class HttpUtil {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    public static  <T> T get(String url, Class<T> tClass, Object... params) {
        return REST_TEMPLATE.getForEntity(url, tClass, params).getBody();
    }
}
