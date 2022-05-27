package com.pandora.infrastructure.common.isolator;


import com.pandora.domain.user.model.Role;

import java.util.List;

public interface Isolator {

    String getString(String string);

    List<Role> getRolesByTokenId(String tokenId);
}
