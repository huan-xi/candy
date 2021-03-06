package com.huanxi.good;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDubboConfiguration
public class CandyGoodWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandyGoodWebApplication.class, args);
    }
}
