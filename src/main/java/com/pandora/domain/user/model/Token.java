package com.pandora.domain.user.model;

import com.pandora.infrastructure.util.CommonUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Token {
     private String id;
     private String userId;
     private LocalDateTime lastModifyTime;
     private LocalDateTime createTime;

     public static Token from(String userId) {
         Token token = new Token();
         token.setId(CommonUtil.generateId());
         token.setUserId(userId);
         return token;
     }

     public boolean isExpire() {
         return lastModifyTime.plusMinutes(30).compareTo(LocalDateTime.now()) < 0;
     }

     public static Token mock() {
         Token token = new Token();
         token.setId("token-id");
         token.setUserId("user-id");
         LocalDateTime now = LocalDateTime.now();
         token.setCreateTime(now);
         token.setLastModifyTime(now);
         return token;
     }
}
