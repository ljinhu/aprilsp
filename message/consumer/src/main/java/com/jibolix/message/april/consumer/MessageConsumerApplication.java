package com.jibolix.message.april.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/22 14:36
 * @Description: 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.jibolix.message.april.consumer.feign"})
@ComponentScan(basePackages = {"com.jimbolix.april.common.exception","com.jibolix.message.april.consumer"})
public class MessageConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageConsumerApplication.class,args);
    }
}