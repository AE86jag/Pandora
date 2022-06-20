package com.pandora.infrastructure.common.isolator;

import com.pandora.domain.user.model.Token;
import com.pandora.domain.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("default")
@Slf4j
public class MockIsolator implements Isolator{

    @Override
    public String getString(String string) {
        return "hhh";
    }

    @Override
    public User findUserByUserId(String userId)  {
        return User.mock();
    }

    @Override
    public Token getTokenByTokenId(String tokenId) {
        return Token.mock();
    }
}
