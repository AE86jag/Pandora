package com.pandora.ui.user.vo;

import com.google.common.collect.Lists;
import com.pandora.domain.fund.product.model.UserPosition;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserPositionVO {

    private BigDecimal amount;

    private List<UserPosition> userPositions;

    public static UserPositionVO from(List<UserPosition> userPositions) {
        if (userPositions == null) {
            userPositions = Lists.newArrayList();
        }
        BigDecimal allAmount = userPositions.stream()
                .map(UserPosition::getCurrentAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        UserPositionVO userPositionVO = new UserPositionVO();
        userPositionVO.setAmount(allAmount);
        userPositionVO.setUserPositions(userPositions);
        return userPositionVO;
    }
}
