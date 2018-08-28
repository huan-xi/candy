package com.huanxi.user.config;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class Config {
    @Bean
    public ServletRegistrationBean<KaptchaServlet> getServletRegistrationBean() {  //一定要返回ServletRegistrationBean
        ServletRegistrationBean<KaptchaServlet> bean = new ServletRegistrationBean<>(new KaptchaServlet());     //放入自己的Servlet对象实例
        bean.addUrlMappings("/user/captcha.jpg");  //访问路径值
        bean.addInitParameter("kaptcha.border","no" );
        bean.addInitParameter("kaptcha.textproducer.char.space","5" );
        bean.addInitParameter("kaptcha.textproducer.char.length","5" );
        return bean;
    }
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("5MB");
        //设置总上传数据总大小
        return factory.createMultipartConfig();
    }
}
