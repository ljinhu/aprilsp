package com.jibolix.message.april.consumer.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/26 14:36
 * @Description: 
 */
@Component
@Slf4j
public class ProFeignClientFallBack implements ProFeignClient {
    public Map test() throws Exception{
        log.info("@@@@@@@@熔断@@@@@@@@@@@");
        Map map = new HashMap(2);
        map.put("code","500");
        map.put("message","fegin 熔断");
        return map;
    }
}