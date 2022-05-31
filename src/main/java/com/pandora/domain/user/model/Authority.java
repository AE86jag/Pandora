package com.pandora.domain.user.model;

import com.pandora.infrastructure.util.CommonUtil;
import lombok.Data;

@Data
public class Authority {
    private String id;
    private String userId;
    private Role authority;

    public static Authority buildNormal(String userId) {
        Authority authority = new Authority();
        authority.setId(CommonUtil.generateId());
        authority.setUserId(userId);
        authority.setAuthority(Role.NORMAL);
        return authority;
    }

    public static Authority buildAdmin(String userId) {
        Authority authority = new Authority();
        authority.setId(CommonUtil.generateId());
        authority.setUserId(userId);
        authority.setAuthority(Role.ADMIN);
        return authority;
    }
}
