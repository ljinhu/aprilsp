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
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        Queue queue = new Queue(RabbitMqConstants.route_gateway_queue_name, false);
        return queue;
    }

    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange(RabbitMqConstants.route_gateway_topic_exchange);
        return topicExchange;
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(RabbitMqConstants.route_gateway_bingding_key);
        return binding;
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter(objectMapper));
    }

    @Bean
    public EventConsumer eventConsumer() {
        return new EventConsumer();
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(EventConsumer eventConsumer, MessageConverter messageConverter) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();
        messageListenerAdapter.setDelegate(eventConsumer);
        messageListenerAdapter.setMessageConverter(messageConverter);
        return messageListenerAdapter;
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(Queue queue, MessageListenerAdapter messageListenerAdapter, ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        listenerContainer.addQueueNames(queue.getName());
        listenerContainer.setConcurrentConsumers(1);
        listenerContainer.setMessageListener(messageListenerAdapter);
        return listenerContainer;
    }
}