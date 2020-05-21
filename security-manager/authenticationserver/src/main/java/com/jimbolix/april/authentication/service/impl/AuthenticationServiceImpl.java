package com.jimbolix.april.authentication.service.impl;

import com.jimbolix.april.authentication.domain.SysMenuEntity;
import com.jimbolix.april.authentication.service.AuthenticationService;
import com.jimbolix.april.authentication.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @ClassName AuthenticationServiceImpl
 * @Author liruihui
 * @date 2020.05.17 10:25
 */
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private ResourceService resources;
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";
    @Override
    public boolean decide(HttpServletRequest authRequest) {
        String method = authRequest.getMethod();
        String servletPath = authRequest.getServletPath();
        log.info("@@@@请求的地址是｛｝，方法是｛｝@@@@",servletPath,method);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ConfigAttribute configAttribute = resources.findConfigAttributesByUrl(authRequest);
        //系统资源中无指定资源则直接返回false,业务变更，需要保护的资源全都需要配置到资源池中，否则放行
        if(StringUtils.equals(NONEXISTENT_URL,configAttribute.getAttribute())){
            return true;
        }
        //获取用户所有资源
        List<SysMenuEntity> resources = this.resources.findResourcesByUserAccont(authentication.getName());
        return isMatch(resources,configAttribute);
    }

    private boolean isMatch(List<SysMenuEntity> resources,ConfigAttribute configAttribute){
        return resources.stream().anyMatch(resource -> {
            String menuId = resource.getMenuId().toString();
            return menuId.equals(configAttribute.getAttribute());
                }
        );
    }
}
