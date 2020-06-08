package com.jibolix.message.april.consumer.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jibolix.message.april.consumer.event.MessageConsumer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/22 14:43
 * @Description: 
 */
@Configuration
public class RabbitConfig {

    @Bean
    public MessageConverter messageConverter(){
        ObjectMapper obj = new ObjectMapper();
        obj.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(obj);
        return new ContentTypeDelegatingMessageConverter(converter);
    }

    @Bean
    public MessageConsumer messageConsumer(){
        MessageConsumer messageConsumer = new MessageConsumer();
        return messageConsumer;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(){
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter();
        listenerAdapter.setMessageConverter(messageConverter());
        listenerAdapter.setDelegate(messageConsumer());
        Map<String, String> queueOrTagToMethodNameMap = new HashMap<String, String>();
        queueOrTagToMethodNameMap.put("april_message","consumerString");
        listenerAdapter.setQueueOrTagToMethodName(queueOrTagToMethodNameMap);
        return listenerAdapter;
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        listenerContainer.setMessageListener(messageListenerAdapter());
        listenerContainer.setQueueNames("april_message");
        return listenerContainer;
    }
}