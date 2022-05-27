package com.pandora.domain.convertiblebond.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConvertibleBondShotRegister {

    private String id;
    private String bondCode;
    private String userId;
    private String email;
    private Boolean isSend;
    private LocalDateTime sendTime;
    private LocalDateTime lastModifyTime;
    private LocalDateTime createTime;

}
