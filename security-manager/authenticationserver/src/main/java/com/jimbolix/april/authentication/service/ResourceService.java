package com.jimbolix.april.authentication.service;

import com.jimbolix.april.authentication.domain.SysMenuEntity;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description 资源接口
 * @ClassName ResourceService
 * @Author liruihui
 * @date 2020.05.17 09:24
 */
public interface ResourceService {

    /**
     * 加载权限资源数据
     */
    Map<RequestMatcher, ConfigAttribute> loadResource();

    ConfigAttribute findConfigAttributesByUrl(HttpServletRequest servletRequest);

    List<SysMenuEntity> findResourcesByUserAccont(String userAccount);
}
