package com.pandora.infrastructure.common.isolator;

import com.google.common.collect.Lists;
import com.pandora.domain.user.model.Role;
import com.pandora.domain.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("default")
@Slf4j
public class MockIsolator implements Isolator{

    @Override
    public String getString(String string) {
        return "hhh";
    }

    @Override
    public List<Role> getRolesByTokenId(String tokenId) {
        return Lists.newArrayList(Role.NORMAL, Role.ADMIN);
    }
}
