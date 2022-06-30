package com.pandora.domain.user.service;

import com.pandora.domain.user.model.UserToken;

public interface IUserService {
    UserToken login(String code);

    void userInfoUpdate(String email, String mobile);

    void exception();
}
