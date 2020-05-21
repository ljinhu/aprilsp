package com.jimbolix.april.gateway.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/21 15:40
 * @Description: 
 */
@Configuration
public class Feignconfig {

    @Bean
    Logger.Level feignLog(){
        return Logger.Level.FULL;
    }
}