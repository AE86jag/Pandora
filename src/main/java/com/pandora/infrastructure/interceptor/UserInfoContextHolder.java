package com.pandora.infrastructure.interceptor;

import com.pandora.domain.user.model.User;

public class UserInfoContextHolder {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void set(User user) {
        userThreadLocal.set(user);
    }

    public static User get() {
        return userThreadLocal.get();
    }


    public static void remove() {
        userThreadLocal.remove();
    }
}
