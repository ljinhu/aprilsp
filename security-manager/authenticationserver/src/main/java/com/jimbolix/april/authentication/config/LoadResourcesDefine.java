package com.jimbolix.april.authentication.config;

import com.jimbolix.april.authentication.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description
 * @ClassName LoadResourcesDefine
 * @Author liruihui
 * @date 2020.05.17 13:58
 */
@Component
public class LoadResourcesDefine {
    @Autowired
    private ResourceService resourceService;

    @Bean
    public Map<RequestMatcher, ConfigAttribute> loadResources() {
        return resourceService.loadResource();
    }
}
