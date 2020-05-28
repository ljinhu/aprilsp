package com.jibolix.message.april.consumer.controller;

import com.jibolix.message.april.consumer.feign.ProFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/26 14:37
 * @Description: 
 */
@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @Autowired
    private ProFeignClient proFeignClient;

    @GetMapping("/test")
    public Map test() throws Exception{
        log.info("@@@@@@@index接受到请求@@@@@@@@@");
        return proFeignClient.test();
    }
}