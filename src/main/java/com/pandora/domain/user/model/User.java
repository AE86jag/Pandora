package com.pandora.domain.user.model;

import com.google.common.collect.Lists;
import com.pandora.infrastructure.util.CommonUtil;
import com.pandora.infrastructure.wx.model.WxLoginResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class User {
    private String id;
    private String mobile;
    private String email;
    private String openId;
    private String unionId;
    private Integer status;
    private LocalDateTime lastModifyTime;
    private LocalDateTime createTime;

    List<Authority> authorities;

    public static User from(WxLoginResponse response) {
        User user = new User();
        String id = CommonUtil.generateId();
        user.setId(id);
        user.setOpenId(response.getOpenId());
        user.setUnionId(response.getUnionId());
        user.setStatus(1);
        user.setAuthorities(Lists.newArrayList(Authority.buildNormal(id)));
        return user;
    }

    public static User mock() {
        User user = new User();
        String id = "user-id";
        user.setId(id);
        user.setOpenId("open-id");
        user.setUnionId("union-id");
        user.setStatus(1);
        user.setAuthorities(Lists.newArrayList(Authority.buildNormal(id)));
        return user;
    }
}
