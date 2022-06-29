package com.pandora.domain.user.model;

import lombok.Data;

@Data
public class UserToken {
    private String token;

    private User user;

    public static UserToken from(String tokenId, User user) {
        UserToken userToken = new UserToken();
        userToken.setToken(tokenId);
        userToken.setUser(user);
        return userToken;
    }
}
