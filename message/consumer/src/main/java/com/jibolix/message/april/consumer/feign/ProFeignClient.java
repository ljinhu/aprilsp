package com.jibolix.message.april.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/26 14:36
 * @Description: 
 */
@FeignClient(name = "producer",fallback = ProFeignClientFallBack.class)
public interface ProFeignClient {

    @GetMapping("/fc/test")
    Map test() throws Exception;
}