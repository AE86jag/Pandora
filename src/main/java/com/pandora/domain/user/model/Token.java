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
}
