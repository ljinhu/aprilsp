package com.jimbolix.april.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients("com.jimbolix.april.common.feign")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.jimbolix.april.ware.dao")
public class AprilWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(AprilWareApplication.class, args);
    }

}
