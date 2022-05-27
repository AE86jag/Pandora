package com.pandora.ui.user.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private String token;

    public static UserLoginVO from(String tokenId) {
        UserLoginVO vo = new UserLoginVO();
        vo.setToken(tokenId);
        return vo;
    }
}
