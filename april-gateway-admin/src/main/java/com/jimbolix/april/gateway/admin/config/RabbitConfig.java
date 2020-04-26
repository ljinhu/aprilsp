package com.jimbolix.april.gateway.admin.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimbolix.april.common.constant.RabbitMqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }

    @Bean
    Queue queue() {
        log.info("@@@@@声明网关路由的消息队列，队列名称{}@@@@", RabbitMqConstants.route_gateway_queue_name);
        Queue queue = new Queue(RabbitMqConstants.route_gateway_queue_name, false);
        return queue;
    }

    @Bean
    TopicExchange topicExchange() {
        log.info("@@@@声明网关消息的交换机，{}@@@@", RabbitMqConstants.route_gateway_topic_exchange);
        TopicExchange topicExchange = new TopicExchange(RabbitMqConstants.route_gateway_topic_exchange);
        return topicExchange;
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange) {
        log.info("@@@@声明网关消息的绑定,{}@@@@", RabbitMqConstants.route_gateway_bingding_key);
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConstants.route_gateway_bingding_key);
        return binding;
    }

}