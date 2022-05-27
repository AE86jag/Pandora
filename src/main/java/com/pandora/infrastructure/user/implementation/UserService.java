package com.pandora.infrastructure.user.implementation;

import com.pandora.domain.user.mapper.TokenMapper;
import com.pandora.domain.user.mapper.UserMapper;
import com.pandora.domain.user.model.Token;
import com.pandora.domain.user.model.User;
import com.pandora.domain.user.service.IUserService;
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
    public String login(String code) {
        WxLoginResponse response = wxClient.login(code);
        User user = userMapper.findByOpenIdWithAuthorities(response.getOpenId());

        if (user == null) {
            user = User.from(response);
            userMapper.insert(user);
            userMapper.insertAuthority(user.getAuthorities());
            Token token = Token.from(user.getId());
            tokenMapper.insert(token);
            return token.getId();
        }

        Token token = tokenMapper.findByUserId(user.getId());
        if (token != null) {
            tokenMapper.extend(token.getId());
            return token.getId();
        }

        Token newToken = Token.from(user.getId());
        tokenMapper.insert(newToken);
        return newToken.getId();
    }
}
