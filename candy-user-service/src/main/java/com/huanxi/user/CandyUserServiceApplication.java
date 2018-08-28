package com.huanxi.user;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class CandyUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandyUserServiceApplication.class, args);
    }
}
