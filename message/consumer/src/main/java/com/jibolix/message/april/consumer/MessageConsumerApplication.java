package com.jibolix.message.april.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/22 14:36
 * @Description: 
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.jibolix.message.april.consumer.feign"})
public class MessageConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageConsumerApplication.class,args);
    }
}