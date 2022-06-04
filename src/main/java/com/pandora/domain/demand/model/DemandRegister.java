package com.pandora.domain.demand.model;

import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.util.CommonUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DemandRegister {

    private String id;

    private String name;

    private String userId;

    private String contact;

    private String demandDetail;

    private Integer status;

    private LocalDateTime lastModifyTime;

    private LocalDateTime createTime;


    public static DemandRegister from(String name, String contact, String demandDetail) {
        DemandRegister register = new DemandRegister();
        register.setId(CommonUtil.generateId());
        register.setName(name);
        register.setUserId(CurrentUserUtils.currenUserId());
        register.setContact(contact);
        register.setDemandDetail(demandDetail);
        register.setStatus(0);
        return register;
    }
}
