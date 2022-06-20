package com.pandora.infrastructure.common;

import com.pandora.domain.user.model.User;
import com.pandora.infrastructure.interceptor.UserInfoContextHolder;

public class CurrentUserUtils {

    private CurrentUserUtils () {

    }

    public static String currentUserId() {
        User user = UserInfoContextHolder.get();
        return user != null ? user.getId() : null;
    }
}
