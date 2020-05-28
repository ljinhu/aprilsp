package com.jimbolix.april.message.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: ruihui.li
 * @Date: 2020/5/22 14:42
 * @Description: 
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AprilMessageProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AprilMessageProducerApplication.class,args);
    }
}