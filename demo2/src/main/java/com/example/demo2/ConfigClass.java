package com.example.demo2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:spring/spring-security.xml"})
public class ConfigClass {
}
