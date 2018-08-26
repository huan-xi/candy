package com.huanxi.user.config;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {  //一定要返回ServletRegistrationBean
        ServletRegistrationBean bean = new ServletRegistrationBean(new KaptchaServlet());     //放入自己的Servlet对象实例
        bean.addUrlMappings("/captcha.jpg");  //访问路径值
        bean.addInitParameter("kaptcha.border","no" );
        bean.addInitParameter("kaptcha.textproducer.char.space","5" );
        bean.addInitParameter("kaptcha.textproducer.char.length","5" );
        return bean;
    }
}
