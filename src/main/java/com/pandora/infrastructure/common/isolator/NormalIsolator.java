package com.pandora.infrastructure.common.isolator;

import com.pandora.domain.user.mapper.TokenMapper;
import com.pandora.domain.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class NormalIsolator implements Isolator {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public String getString(String string) {
        return null;
    }

    @Override
    public User findUserByTokenId(String tokenId) {
        return tokenMapper.findByTokenId(tokenId);
    }


}
