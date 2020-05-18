package com.jimbolix.april.authentication.service.impl;

import com.jimbolix.april.authentication.domain.SysMenuEntity;
import com.jimbolix.april.authentication.feign.ResourceProvider;
import com.jimbolix.april.authentication.service.AprilMvcRequestMatcher;
import com.jimbolix.april.authentication.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @ClassName ResourceServiceImpl
 * @Author liruihui
 * @date 2020.05.17 09:27
 */
@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceProvider resourceProvider;
    @Autowired
    private HandlerMappingIntrospector handlerMappingIntrospector;

    /**
     * 未在资源库中的URL默认标识
     */
    public static final String NONEXISTENT_URL = "NONEXISTENT_URL";

    /**
     * 系统中所有权限的集合
     */
    private Map<RequestMatcher, ConfigAttribute> resourceConfigAttributes;

    public Map<RequestMatcher, ConfigAttribute> loadResource() {
        List<SysMenuEntity> sysMenuEntities = resourceProvider.allResources();
        if(!CollectionUtils.isEmpty(sysMenuEntities)){
            log.info("@@@系统所有权限,共｛｝条权限信息@@@",sysMenuEntities.size());
            resourceConfigAttributes = sysMenuEntities.stream().filter(sysMenuEntity -> StringUtils.isNotEmpty(sysMenuEntity.getUrl()) && StringUtils.isNotEmpty(sysMenuEntity.getPerms())).collect(Collectors.toMap(
                    sysMenu -> this.aprilMvcRequestMatcher(sysMenu.getUrl(), sysMenu.getPerms()),
                    sysMenu -> new SecurityConfig(sysMenu.getMenuId().toString())
            ));
        }
        return resourceConfigAttributes;
    }

    @Override
    public ConfigAttribute findConfigAttributesByUrl(HttpServletRequest servletRequest) {
        if(CollectionUtils.isEmpty(resourceConfigAttributes)){
            log.info("@@@@资源池中数据为空@@@@");
            return new SecurityConfig(NONEXISTENT_URL);
        }

        return resourceConfigAttributes.keySet().stream().
                filter(requestMatcher -> requestMatcher.matches(servletRequest))
                .map(requestMatcher -> resourceConfigAttributes.get(requestMatcher))
                .findFirst().orElse(new SecurityConfig(NONEXISTENT_URL));
    }

    @Override
    public List<SysMenuEntity> findResourcesByUserAccont(String userAccount) {
        List<SysMenuEntity> userMenuList = resourceProvider.getUserMenuList(userAccount);
        return userMenuList;
    }


    private AprilMvcRequestMatcher aprilMvcRequestMatcher(String url,String method){
        return new AprilMvcRequestMatcher(handlerMappingIntrospector,method,url);
    }
}
