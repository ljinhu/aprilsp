package com.jimbolix.april.message.producer.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/6/3 15:39
 * @Description: 
 */
@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessageConverter messageConverter;
    @PostConstruct
    public void postConstruct(){
        rabbitTemplate.setMessageConverter(messageConverter);
    }

    public void sendMapMessage(Map message){
        rabbitTemplate.convertAndSend("april_exchange","april_bingding_key",message);
    }

    public void sendMessage(String s){
        rabbitTemplate.convertAndSend("april_exchange","april_bingding_key",s);
    }
}