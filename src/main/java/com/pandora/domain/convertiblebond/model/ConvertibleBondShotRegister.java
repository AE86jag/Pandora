package com.pandora.domain.convertiblebond.model;

import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.util.CommonUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ConvertibleBondShotRegister {

    private String id;
    private String bondCode;
    private String bondName;
    private String userId;
    private String email;
    private Boolean isSend;
    private LocalDateTime sendTime;
    private LocalDateTime lastModifyTime;
    private LocalDateTime createTime;

    public static ConvertibleBondShotRegister from(String bondCode, String bondName, String email) {
        ConvertibleBondShotRegister register = new ConvertibleBondShotRegister();
        register.setId(CommonUtil.generateId());
        register.setBondCode(bondCode);
        register.setBondName(bondName);
        register.setUserId(CurrentUserUtils.currenUserId());
        register.setEmail(email);
        register.setIsSend(false);
        return register;

    }

}
