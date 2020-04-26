package com.jimbolix.april.gateway.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jimbolix.april.common.utils.PageUtils;
import com.jimbolix.april.gateway.admin.entity.GatewayRouteEntity;

import java.util.Map;

/**
 * 网关路由表
 *
 * @author liruihui
 * @email liruihui
 * @date 2020-04-23 21:30:04
 */
public interface GatewayRouteService extends IService<GatewayRouteEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 添加路由
     * 1.保存
     * 2.GatewayRouteEntity 转化为 org.springframework.cloud.gateway.route.RouteDefinition
     * 并发送消息到rabbitmq上
     * @param gatewayRouteEntity
     * @return
     */
    boolean add(GatewayRouteEntity gatewayRouteEntity);

    boolean overLoadRoute();
}

