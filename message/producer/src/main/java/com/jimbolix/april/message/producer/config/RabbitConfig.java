package com.jimbolix.april.message.producer.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/29 16:44
 * @Description:
 */
@Configuration
public class RabbitConfig {

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        Jackson2JsonMessageConverter jjconvert = new Jackson2JsonMessageConverter(objectMapper);
        return new ContentTypeDelegatingMessageConverter(jjconvert);
    }

    @Bean
    public Queue topicQueue() {
        Queue queue = new Queue("april_message", true);
        return queue;
    }

    @Bean
    public Exchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange("april_exchange");
        return topicExchange;
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("april_bingding_key").noargs();
    }

}