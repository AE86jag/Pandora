package com.pandora.infrastructure.util;

import java.util.UUID;

public class CommonUtil {

    private CommonUtil() {

    }

    public static String generateId() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
