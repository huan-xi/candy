package com.huanxi.user;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDubboConfiguration
@ComponentScan(value = "com.huanxi")
public class CandyUserWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandyUserWebApplication.class, args);
    }
}
