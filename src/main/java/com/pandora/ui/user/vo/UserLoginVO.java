package com.pandora.ui.user.vo;

import com.pandora.domain.user.model.User;
import com.pandora.domain.user.model.UserToken;
import lombok.Data;

@Data
public class UserLoginVO {
    private String token;

    private UserVO user;

    public static UserLoginVO from(UserToken userToken) {
        UserLoginVO vo = new UserLoginVO();
        vo.setToken(userToken.getToken());
        vo.setUser(UserVO.from(userToken.getUser()));
        return vo;
    }

    @Data
    public static class UserVO {
        private String email;
        private String mobile;

        public static UserVO from(User user) {
            UserVO vo = new UserVO();
            vo.setEmail(user.getEmail());
            vo.setMobile(user.getMobile());
            return vo;
        }

        public static UserVO from(String email, String mobile) {
            UserVO vo = new UserVO();
            vo.setEmail(email);
            vo.setMobile(mobile);
            return vo;
        }
    }
}
