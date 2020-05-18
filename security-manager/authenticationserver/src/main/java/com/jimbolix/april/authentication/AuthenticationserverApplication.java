package com.jimbolix.april.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @ClassName AuthenticationserverApplication
 * @Author liruihui
 * @date 2020.05.16 17:26
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.jimbolix.april.authentication.feign")
public class AuthenticationserverApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AuthenticationserverApplication.class);
        springApplication.run(args);
    }
}
