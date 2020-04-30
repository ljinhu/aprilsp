package com.jimbolix.april.gateway.admin.sender;

import com.jimbolix.april.common.constant.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/4/26 08:33
 * @Description: 网关消息发送者
 */
@Component
@Slf4j
public class BusSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageConverter messageConverter;

    @PostConstruct
    public void init() {
        log.info("@@@@设置messageConverter@@@@");
        rabbitTemplate.setMessageConverter(messageConverter);
    }

    public void send(RouteDefinition routeDefinition) {
        log.info("@@@@发送路由添加消息id是{}@@@@", routeDefinition.getId());
        rabbitTemplate.convertAndSend(RabbitMqConstants.route_gateway_topic_exchange, "route_gateway_add_bingding.add", routeDefinition);
    }

    public void sendDelMessage(String routeId){
        log.info("@@@@发送路由删除消息，id是{}@@@@",routeId);
        rabbitTemplate.convertAndSend(RabbitMqConstants.route_gateway_topic_exchange,"route_gateway_del_bingding.del",routeId);
    }

}