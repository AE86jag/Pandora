package com.pandora.ui.demand.command;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class CreateDemandCommand {

    private String name;

    private String contact;

    private String demandDetail;

    public void check() {
        if (StringUtils.isEmpty(name)) {
            throw new RuntimeException("请输入姓名");
        }
        if (StringUtils.isEmpty(contact)) {
            throw new RuntimeException("请输入联系方式");
        }
        if (StringUtils.isEmpty(demandDetail)) {
            throw new RuntimeException("请输入需求详情");
        }
    }
}
