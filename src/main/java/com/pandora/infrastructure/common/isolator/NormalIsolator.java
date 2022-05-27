package com.pandora.infrastructure.common.isolator;

import com.pandora.domain.user.mapper.TokenMapper;
import com.pandora.domain.user.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class NormalIsolator implements Isolator {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public String getString(String string) {
        return null;
    }

    @Override
    public List<Role> getRolesByTokenId(String tokenId) {
        return tokenMapper.findAuthoritiesByTokenId(tokenId);
    }


}
