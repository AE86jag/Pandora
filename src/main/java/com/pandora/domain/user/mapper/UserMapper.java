package com.pandora.domain.user.mapper;

import com.pandora.domain.user.model.Authority;
import com.pandora.domain.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int insert(User user);

    User findByOpenIdWithAuthorities(String openId);

    int insertAuthority(List<Authority> authorities);

    User findByIdWithAuthority(String id);

    Integer updateEmailAndMobileById(@Param("id")String id, @Param("mobile")String mobile, @Param("email")String email);
}
