package com.jimbolix.april.common.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimbolix.april.common.utils.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/20 10:30
 * @Description: 用户拦截器，在这里主要用来从token中获取用户信息
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("@@@@执行获取用户信息方法@@@@");
        String token = StringUtils.defaultIfBlank(request.getHeader(X_CLIENT_TOKEN_USER),"{}");
        Map map = new ObjectMapper().readValue(token, Map.class);
        UserContextHolder.getInstance().setContext(map);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("@@@清除上线文@@@");
        UserContextHolder.getInstance().clearContext();
    }
}