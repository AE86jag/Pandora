package com.pandora.domain.convertiblebond.model;

import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.util.CommonUtil;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ConvertibleBondShotRegister {

    private String id;
    private String subscriptionCode;
    private String bondName;
    private String userId;
    private String email;
    private Boolean isSend;
    private LocalDateTime sendTime;
    private LocalDateTime lastModifyTime;
    private LocalDateTime createTime;

    public static ConvertibleBondShotRegister from(String subscriptionCode, String bondName, String email) {
        ConvertibleBondShotRegister register = new ConvertibleBondShotRegister();
        register.setId(CommonUtil.generateId());
        register.setSubscriptionCode(subscriptionCode);
        register.setBondName(bondName);
        register.setUserId(CurrentUserUtils.currentUserId());
        register.setEmail(email);
        register.setIsSend(false);
        return register;

    }

    public String getEmailNotifyContent() {
        return String.format("您好！您中签的可转债: %s（债券代码: %s）将于今天（%s）上市，请及时卖出",
                bondName, subscriptionCode, LocalDate.now());
    }

}
