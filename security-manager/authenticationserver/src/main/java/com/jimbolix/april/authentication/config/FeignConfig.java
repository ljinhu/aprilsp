package com.jimbolix.april.authentication.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/21 16:12
 * @Description: 
 */
@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}