package com.pandora.ui.user.controller;

import com.pandora.domain.user.model.Role;
import com.pandora.domain.user.model.UserToken;
import com.pandora.domain.user.service.IUserService;
import com.pandora.infrastructure.interceptor.HasAnyRole;
import com.pandora.ui.user.UserLoginCommand;
import com.pandora.ui.user.command.UserInfoUpdateCommand;
import com.pandora.ui.user.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/login")
    public UserLoginVO login(@RequestBody UserLoginCommand command) {
        UserToken userToken = iUserService.login(command.getCode());
        return UserLoginVO.from(userToken);
    }

    @PutMapping
    @HasAnyRole(Role.NORMAL)
    public UserLoginVO.UserVO userInfoUpdate(@RequestBody UserInfoUpdateCommand command) {
        command.check();
        iUserService.userInfoUpdate(command.getEmail(), command.getMobile());
        return UserLoginVO.UserVO.from(command.getEmail(), command.getMobile());
    }
}
