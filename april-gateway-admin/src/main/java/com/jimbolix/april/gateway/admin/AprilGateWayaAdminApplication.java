package com.jimbolix.april.gateway.admin;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @ClassName AprilGateWayApplication
 * @Author liruihui
 * @date 2020.04.23 20:16
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.jimbolix.april")
public class AprilGateWayaAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AprilGateWayaAdminApplication.class);
    }
}
