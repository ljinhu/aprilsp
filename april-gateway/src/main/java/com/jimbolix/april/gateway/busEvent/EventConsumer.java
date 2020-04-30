package com.jimbolix.april.gateway.busEvent;

import com.jimbolix.april.gateway.appEvent.ReloadRouteEventPublish;
import com.jimbolix.april.gateway.service.RoueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;


/**
 * @Author: ruihui.li
 * @Date: 2020/4/26 14:08
 * @Description: 消息消费者
 */
@Slf4j
public class EventConsumer {
    @Autowired
    private RoueService roueService;
    @Autowired
    private ReloadRouteEventPublish reloadRouteEventPublish;

    public void handleAddMessage(RouteDefinition routeDefinition) {
        log.info("@@@@收到消息，route id 是{}", routeDefinition.getId());
        roueService.save(routeDefinition);
        reloadRouteEventPublish.publishRouteChangeEvent();
    }

    public void handDelMessage(String routeId) {
        log.info("@@@@处理删除路由的消息routeId{}@@@@", routeId);
        roueService.delete(routeId);
        reloadRouteEventPublish.publishRouteChangeEvent();
    }
}