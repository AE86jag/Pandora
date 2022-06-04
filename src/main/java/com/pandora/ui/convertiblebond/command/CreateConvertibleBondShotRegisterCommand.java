package com.pandora.ui.convertiblebond.command;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class CreateConvertibleBondShotRegisterCommand {

    private String email;

    private String bondCode;

    public void check() {
        if (StringUtils.isEmpty(email)) {
            throw new RuntimeException("请输入邮箱");
        }
        if (StringUtils.isEmpty(bondCode)) {
            throw new RuntimeException("请输入债券代码");
        }
    }
}
