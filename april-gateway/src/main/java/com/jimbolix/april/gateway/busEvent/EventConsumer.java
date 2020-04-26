package com.jimbolix.april.gateway.busEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;

/**
 * @Author: ruihui.li
 * @Date: 2020/4/26 14:08
 * @Description: 消息消费者
 */
@Slf4j
public class EventConsumer {

    public void handleMessage(RouteDefinition routeDefinition){
        log.info("@@@@收到消息，route id 是{}",routeDefinition.getId());
    }
}