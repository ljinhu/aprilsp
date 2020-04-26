package com.jimbolix.april.gateway.routes;

import com.jimbolix.april.gateway.service.RoueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author: ruihui.li
 * @Date: 2020/4/26 16:11
 * @Description:
 */
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
    @Autowired
    private RoueService roueService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(roueService.getRouteDefinitions());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            roueService.save(routeDefinition);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(route -> {
            roueService.delete(route);
            return Mono.empty();
        });
    }
}