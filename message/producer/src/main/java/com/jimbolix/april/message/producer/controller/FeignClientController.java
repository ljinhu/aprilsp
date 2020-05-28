package com.jimbolix.april.message.producer.controller;

import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/test")
    public Map test() throws Exception {
        log.info("@@@@接受到请求@@@@@@@@");
        TimeUnit.SECONDS.sleep(3);
        Map map = new HashMap(2);
        map.put("code", "200");
        map.put("message", "成功");
        return map;
    }
}