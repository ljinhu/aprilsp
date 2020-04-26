package com.jimbolix.april.gateway.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

/**
 * @Author: ruihui.li
 * @Date: 2020/4/26 16:25
 * @Description: 
 */
public interface RoueService {

    boolean save(RouteDefinition routeDefinition);

    boolean delete(String id);

    Collection<RouteDefinition> getRouteDefinitions();
}