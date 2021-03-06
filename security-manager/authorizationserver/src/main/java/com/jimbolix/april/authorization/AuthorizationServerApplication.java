package com.jimbolix.april.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @ClassName com.jimbolix.april.authorization.config.AuthorizationServerApplication
 * @Author liruihui
 * @date 2020.05.10 18:47
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.jimbolix.april.authorization.feign"})
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AuthorizationServerApplication.class);
        springApplication.run(args);
    }
}
