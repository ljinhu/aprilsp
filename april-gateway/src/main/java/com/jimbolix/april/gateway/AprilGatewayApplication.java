package com.jimbolix.april.gateway;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 1、开启服务注册发现
 * (配置nacos的注册中心地址)
 * 2、编写网关配置文件
 */

@EnableDiscoveryClient
//@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCreateCacheAnnotation
@EnableFeignClients(basePackages = {"com.jimbolix.april.gateway.feign"})
public class AprilGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AprilGatewayApplication.class, args);
    }

}
