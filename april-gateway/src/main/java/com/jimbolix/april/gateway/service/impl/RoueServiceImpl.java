package com.jimbolix.april.gateway.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.jimbolix.april.common.constant.CacheKeysConstant;
import com.jimbolix.april.gateway.service.RoueService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: ruihui.li
 * @Date: 2020/4/26 16:27
 * @Description:
 */
@Slf4j
@Service
public class RoueServiceImpl implements RoueService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private HashMap<String, RouteDefinition> routeDefinitionMap = new HashMap<>();

    @CreateCache(cacheType = CacheType.REMOTE, name = CacheKeysConstant.gateway_routes)
    private Cache<String, RouteDefinition> gateWayRouteCache;

    @Override
    public boolean save(RouteDefinition routeDefinition) {
        if (routeDefinition != null && StringUtils.isNotEmpty(routeDefinition.getId())) {
            routeDefinitionMap.put(routeDefinition.getId(), routeDefinition);
            return true;
        } else {
            log.info("@@@@保存路由失败@@@@");
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (StringUtils.isNotEmpty(id)) {
            log.info("@@@@移除路由，id:{}", id);
            routeDefinitionMap.remove(id);
        }
        return true;
    }

    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitionMap.values();
    }

    /**
     * 初始化路由信息
     */
    @PostConstruct
    public void loadRouteDefinitions() {
        log.info("@@@@网关服务开始加载路由@@@@");
        Set<String> keys = stringRedisTemplate.keys(CacheKeysConstant.gateway_routes+"*");
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        log.info("@@@@预计初始化网关数据{}条@@@@", keys.size());
        keys = keys.stream().map(key -> key.replace(CacheKeysConstant.gateway_routes, ""))
                .collect(Collectors.toSet());
        Map<String, RouteDefinition> routeCaches = gateWayRouteCache.getAll(keys);

        //jetcache将RouteDefinition返序列化后，uri发生变化，未初使化，导致路由异常，以下代码是重新初使化uri
        routeCaches.values().stream().forEach(routeDefinition -> {
            URI uri = routeDefinition.getUri();
            try {
                routeDefinition.setUri(new URI(uri.toASCIIString()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
                log.error("@@@@重新初始化路由【{}】uri失败@@@@", routeDefinition.getId());
            }
        });

        routeDefinitionMap.putAll(routeCaches);
        log.info("@@@@初始化路由信息结束，共初始化{}条路由信息@@@@", routeDefinitionMap.size());

    }
}