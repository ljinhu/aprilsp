package com.jimbolix.april.authentication.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 权限校验
 * @ClassName AuthenticationService
 * @Author liruihui
 * @date 2020.05.17 10:24
 */
public interface AuthenticationService {

    /**
     * 校验权限
     *
     * @param authRequest
     * @return 是否有权限
     */
    boolean decide(HttpServletRequest authRequest);
}
