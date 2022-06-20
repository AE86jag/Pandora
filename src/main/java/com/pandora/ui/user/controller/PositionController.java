package com.pandora.ui.user.controller;

import com.pandora.domain.fund.product.model.UserPosition;
import com.pandora.domain.user.model.Role;
import com.pandora.domain.user.service.IPositionService;
import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.interceptor.HasAnyRole;
import com.pandora.ui.user.vo.UserPositionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private IPositionService iPositionService;

    @GetMapping
    @HasAnyRole(Role.NORMAL)
    public UserPositionVO getCurrentUserPosition() {
        List<UserPosition> userPositions = iPositionService.calculateUserPosition(CurrentUserUtils.currentUserId());
        return UserPositionVO.from(userPositions);
    }
}
