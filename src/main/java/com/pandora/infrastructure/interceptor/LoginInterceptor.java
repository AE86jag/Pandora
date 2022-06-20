package com.pandora.infrastructure.interceptor;

import com.pandora.domain.user.mapper.TokenMapper;
import com.pandora.domain.user.model.Role;
import com.pandora.domain.user.model.Token;
import com.pandora.domain.user.model.User;
import com.pandora.infrastructure.common.isolator.Isolator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private Isolator isolator;

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        UserInfoContextHolder.remove();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        log.info("this is login interceptor");
        if(handler instanceof HandlerMethod){
            HasAnyRole needAuthority = ((HandlerMethod) handler).getMethodAnnotation(HasAnyRole.class);
            if (needAuthority == null || needAuthority.value().length == 0) {
                return true;
            }
            String authorization = request.getHeader("Authorization");
            if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Token ")) {
                log.info("current user not login");
                response.sendError(401, "请先登录");
                return false;
            }

            String tokenId = authorization.split(" ")[1];
            Token token = isolator.getTokenByTokenId(tokenId);
            if (token == null) {
                log.info("token is null");
                response.sendError(401, "Token为空, 请登录");
                return false;
            }

            if (token.isExpire()) {
                log.info("token is expire");
                tokenMapper.deleteById(tokenId);
                response.sendError(401, "Token过期, 请重新登录");
                return false;
            }

            User user = isolator.findUserByUserId(token.getUserId());
            if (user == null) {
                log.info("user is null");
                response.sendError(401, "用户为空");
                return false;
            }

            List<Role> roles = user.getRoles();
            if(CollectionUtils.isEmpty(roles)) {
                log.info("user has empty roles");
                response.sendError(401, "用户没有角色");
                return false;
            }

            boolean res = Arrays.stream(needAuthority.value()).anyMatch(roles::contains);
            if (res) {
                UserInfoContextHolder.set(user);
                tokenMapper.extend(tokenId);
                return true;
            }
            log.info("current user has no role to visit current interface");
            response.sendError(403, "暂无权限访问");
            return false;

        }
        log.info("handler type is not HandlerMethod");
        return false;
    }
}
