package com.jimbolix.april.gateway.admin.sender;

import com.jimbolix.april.common.constant.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
        log.info("@@@@发送消息[]@@@@", routeDefinition.getId());
        rabbitTemplate.convertAndSend(RabbitMqConstants.route_gateway_topic_exchange, "route_gateway_bingding.add", routeDefinition);
    }

}