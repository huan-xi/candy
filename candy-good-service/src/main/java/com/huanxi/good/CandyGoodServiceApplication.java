package com.huanxi.good;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class CandyGoodServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandyGoodServiceApplication.class, args);
    }
}
