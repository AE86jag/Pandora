package com.pandora.infrastructure.user.implementation;

import com.pandora.domain.user.mapper.TokenMapper;
import com.pandora.domain.user.mapper.UserMapper;
import com.pandora.domain.user.model.Token;
import com.pandora.domain.user.model.User;
import com.pandora.domain.user.model.UserToken;
import com.pandora.domain.user.service.IUserService;
import com.pandora.infrastructure.common.CurrentUserUtils;
import com.pandora.infrastructure.wx.WxClient;
import com.pandora.infrastructure.wx.model.WxLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    @Autowired
    private WxClient wxClient;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    @Transactional
    public UserToken login(String code) {
        WxLoginResponse response = wxClient.login(code);
        User user = userMapper.findByOpenIdWithAuthorities(response.getOpenId());

        if (user == null) {
            user = User.from(response);
            userMapper.insert(user);
            userMapper.insertAuthority(user.getAuthorities());
            Token token = Token.from(user.getId());
            tokenMapper.insert(token);
            return UserToken.from(token.getId(), user);
        }

        Token token = tokenMapper.findByUserId(user.getId());
        if (token != null) {
            tokenMapper.extend(token.getId());
            return UserToken.from(token.getId(), user);
        }

        Token newToken = Token.from(user.getId());
        tokenMapper.insert(newToken);
        return UserToken.from(newToken.getId(), user);
    }

    @Override
    public void userInfoUpdate(String email, String mobile) {
        String userId = CurrentUserUtils.currentUserId();
        userMapper.updateEmailAndMobileById(userId, mobile, email);
    }
}
