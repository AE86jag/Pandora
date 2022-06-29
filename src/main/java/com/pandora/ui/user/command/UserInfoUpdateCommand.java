package com.pandora.ui.user.command;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class UserInfoUpdateCommand {

    private String mobile;

    private String email;

    public void check() {
        if (StringUtils.isEmpty(mobile)) {
            throw new RuntimeException("请输入手机号");
        }
        if (StringUtils.isEmpty(email)) {
            throw new RuntimeException("请输入邮箱");
        }
    }
}
