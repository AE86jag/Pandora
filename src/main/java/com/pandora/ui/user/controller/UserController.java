package com.pandora.ui.user.controller;

import com.pandora.domain.user.service.IUserService;
import com.pandora.ui.user.UserLoginCommand;
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
        String tokenId = iUserService.login(command.getCode());
        return UserLoginVO.from(tokenId);
    }
}
