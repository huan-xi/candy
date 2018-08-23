package com.huanxi.user.service.impl;

import com.huanxi.pojo.User;
import org.junit.Test;
public class UserServiceImplTest {
    UserServiceImpl userService=new UserServiceImpl();
    @Test
    public void getUserInfo() {
        userService.getUserInfo();
    }

    @Test
    public void regUser() {
    }

    @Test
    public void regUser1() {
       User user= new User();
       userService.regUser(user);
    }
}