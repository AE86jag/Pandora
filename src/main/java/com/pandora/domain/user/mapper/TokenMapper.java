package com.pandora.domain.user.mapper;

import com.pandora.domain.user.model.Role;
import com.pandora.domain.user.model.Token;
import com.pandora.domain.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TokenMapper {

    int insert(Token token);

    Token findByUserId(String userId);

    int deleteByUserId(String userId);

    int extend(String id);

    List<Role> findAuthoritiesByTokenId(String tokenId);

    User findByTokenId(String id);
}
