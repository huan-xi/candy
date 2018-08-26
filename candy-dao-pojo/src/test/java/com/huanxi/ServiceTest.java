package com.huanxi;

import com.huanxi.dao.UserMapper;
import com.huanxi.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
    UserMapper userMapper = (UserMapper) context.getBean("userMapper");
    @Test
    public void findUser(){
        System.out.println(userMapper.findUserByPhone("fdsa"));
    }
    @Test
    public void test() {

//        System.out.println(userMapper.selectByPrimaryKey((long) 1));
        User user=new User();
        user.setEmail("fdas");
        user.setPhone("564656");
        user.setUsername("fadsf");
        user.setPassword("fdas");
        userMapper.insert(user);
    }
}