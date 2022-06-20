package com.pandora.infrastructure.common.isolator;

import com.pandora.domain.user.model.Token;
import com.pandora.domain.user.model.User;


public interface Isolator {

    String getString(String string);

    User findUserByUserId(String userId);

    Token getTokenByTokenId(String tokenId);
}
