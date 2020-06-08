package com.jibolix.message.april.consumer.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jibolix.message.april.consumer.domain.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/29 16:21
 * @Description:
 */
@Slf4j
public class MessageConsumer {

    public void consumerMap(Map map) {
        Set<Map.Entry> set = map.entrySet();

        for (Map.Entry entry : set) {
            log.info("收到的消息key是{}，value是{}", entry.getKey(), entry.getValue());
        }
    }

    public void consumerUser(User user){
        log.info("消费消息，用户名{}，年龄{}",user.getName(),user.getAge());
    }

    public void consumer(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(object);
        log.info("消费消息{}",s);
    }

    public void consumerString(String s) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(s, User.class);
        log.info("消费消息，用户名{}，年龄{}",user.getName(),user.getAge());
    }



}