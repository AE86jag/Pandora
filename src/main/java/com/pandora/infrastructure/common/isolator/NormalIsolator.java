package com.pandora.infrastructure.common.isolator;

import com.pandora.domain.user.mapper.TokenMapper;
import com.pandora.domain.user.mapper.UserMapper;
import com.pandora.domain.user.model.Token;
import com.pandora.domain.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class NormalIsolator implements Isolator {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public String getString(String string) {
        return null;
    }

    @Override
    public User findUserByUserId(String userId) {
        return userMapper.findByIdWithAuthority(userId);
    }

    @Override
    public Token getTokenByTokenId(String tokenId) {
        return tokenMapper.findById(tokenId);
    }


}
