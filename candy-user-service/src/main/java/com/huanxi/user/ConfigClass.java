package com.huanxi.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:spring/applicationContext-dao.xml"})
public class ConfigClass {
}
