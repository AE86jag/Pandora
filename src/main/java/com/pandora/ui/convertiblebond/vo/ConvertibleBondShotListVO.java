package com.pandora.ui.convertiblebond.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pandora.domain.convertiblebond.model.ConvertibleBondShotRegister;
import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.util.CommonUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConvertibleBondShotListVO {

    private String id;
    private String bondCode;
    private String bondName;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime sendTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private LocalDateTime createdTime;

    public static ConvertibleBondShotListVO from(ConvertibleBondShotRegister register) {
        ConvertibleBondShotListVO item = new ConvertibleBondShotListVO();
        item.setId(register.getId());
        item.setBondCode(register.getBondCode());
        item.setBondName(register.getBondName());
        item.setEmail(register.getEmail());
        item.setSendTime(register.getSendTime());
        item.setCreatedTime(register.getCreateTime());
        return item;
    }

}
