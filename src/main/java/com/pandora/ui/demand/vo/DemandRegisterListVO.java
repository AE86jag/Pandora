package com.pandora.ui.demand.vo;

import com.pandora.domain.demand.model.DemandRegister;
import lombok.Data;

@Data
public class DemandRegisterListVO {

    private String name;

    private String contact;

    private String demandDetail;

    private Integer status;

    public static DemandRegisterListVO from(DemandRegister register) {
        DemandRegisterListVO vo = new DemandRegisterListVO();
        vo.setName(register.getName());
        vo.setContact(register.getContact());
        vo.setDemandDetail(register.getDemandDetail());
        vo.setStatus(register.getStatus());
        return vo;
    }
}
