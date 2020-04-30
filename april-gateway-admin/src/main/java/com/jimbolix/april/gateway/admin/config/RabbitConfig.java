package com.jimbolix.april.gateway.admin.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimbolix.april.common.constant.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/4/24 17:01
 * @Description:
 */
@Configuration
@Slf4j
public class RabbitConfig {

    @Bean
    MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);
        return new ContentTypeDelegatingMessageConverter(converter);
    }

    @Bean
    Queue queue() {
        log.info("@@@@@声明网关路由的消息队列，队列名称{}@@@@", RabbitMqConstants.route_gateway_add_queue_name);
        Queue queue = new Queue(RabbitMqConstants.route_gateway_add_queue_name, false);
        return queue;
    }

    @Bean
    TopicExchange topicExchange() {
        log.info("@@@@声明网关消息的交换机，{}@@@@", RabbitMqConstants.route_gateway_topic_exchange);
        TopicExchange topicExchange = new TopicExchange(RabbitMqConstants.route_gateway_topic_exchange);
        return topicExchange;
    }

    @Bean
    Binding binding(Queue queue , TopicExchange topicExchange) {
        log.info("@@@@声明网关消息的绑定,{}@@@@", RabbitMqConstants.route_gateway_add_bingding_key);
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConstants.route_gateway_add_bingding_key);

        return binding;
    }

    /**
     * 删除的消息队列
     * @return
     */
    @Bean
    Queue delQueue(){
        log.info("@@@@声明路由删除消息队列@@@@");
        Queue queue = new Queue(RabbitMqConstants.route_gateway_del_queue_name,false);
        return queue;
    }

    @Bean
    Binding delBinding(@Qualifier("delQueue") Queue delQueue, TopicExchange topicExchange){
        Binding binding = BindingBuilder.bind(delQueue).to(topicExchange).with(RabbitMqConstants.route_gateway_del_bingding_key);
        return binding;
    }
}