package com.jimbolix.april.message.producer.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimbolix.april.message.producer.domain.Person;
import com.jimbolix.april.message.producer.sender.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/26 14:26
 * @Description: 用于测试feign
 */
@RestController
@RequestMapping("/fc")
@Slf4j
public class FeignClientController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/test")
    public Map test() throws Exception {
        log.info("@@@@接受到请求@@@@@@@@");
        TimeUnit.SECONDS.sleep(3);
        Map map = new HashMap(2);
        map.put("code", "200");
        map.put("message", "成功");
        return map;
    }

    @GetMapping("/msg")
    public Map messagetTest() {
        log.info("@@@@发送消息测试@@@");
        Map map = new HashMap(2);
        map.put("code", "200");
        map.put("message", "成功");
        messageSender.sendMapMessage(map);
        return map;
    }

    @GetMapping("/person")
    public Person getPerson() throws JsonProcessingException {
        Person person = new Person();
        person.setAge("12");
        person.setName("小明");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(person);
        String s1 = JSONObject.toJSONString(person);
        messageSender.sendMessage(s1);
        return person;
    }
}