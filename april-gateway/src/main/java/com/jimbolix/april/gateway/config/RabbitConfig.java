package com.jimbolix.april.gateway.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimbolix.april.common.constant.RabbitMqConstants;
import com.jimbolix.april.gateway.busEvent.EventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
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
 * @Date: 2020/4/26 13:54
 * @Description: rabbitmq配置类
 */
@Configuration
@Slf4j
public class RabbitConfig {

    @Bean
    public Queue queue() {
        Queue queue = new Queue(RabbitMqConstants.route_gateway_add_queue_name, false);
        return queue;
    }

    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange(RabbitMqConstants.route_gateway_topic_exchange);
        return topicExchange;
    }
//
    @Bean
    public Binding binding(@Qualifier("queue") Queue queue, TopicExchange topicExchange) {
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConstants.route_gateway_add_bingding_key);
        return binding;
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }

    /**
     * 删除的消息队列
     *
     * @return
     */
    @Bean
    Queue delQueue() {
        log.info("@@@@声明路由删除消息队列@@@@");
        Queue queue = new Queue(RabbitMqConstants.route_gateway_del_queue_name, false);
        return queue;
    }

    @Bean
    Binding delBinfing(@Qualifier("delQueue") Queue delQueue, TopicExchange topicExchange) {
        Binding binding = BindingBuilder.bind(delQueue).to(topicExchange).with(RabbitMqConstants.route_gateway_del_bingding_key);
        return binding;
    }

    @Bean
    public EventConsumer eventConsumer() {
        return new EventConsumer();
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(EventConsumer eventConsumer, MessageConverter messageConverter) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();
        messageListenerAdapter.setDelegate(eventConsumer);
        Map<String, String> queueOrTagToMethodName = new HashMap<>();
        queueOrTagToMethodName.put(RabbitMqConstants.route_gateway_add_queue_name, "handleAddMessage");
        queueOrTagToMethodName.put(RabbitMqConstants.route_gateway_del_queue_name, "handDelMessage");
        messageListenerAdapter.setMessageConverter(messageConverter);
        messageListenerAdapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
        return messageListenerAdapter;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(@Qualifier("queue") Queue queue,@Qualifier("delQueue")Queue delQueue, MessageListenerAdapter messageListenerAdapter, ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        listenerContainer.addQueueNames(queue.getName(),delQueue.getName());
        listenerContainer.setConcurrentConsumers(1);
        listenerContainer.setMessageListener(messageListenerAdapter);
        return listenerContainer;
    }
}