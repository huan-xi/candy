package com.huanxi.common.message.com.huanxi.common.validate;


import org.junit.Test;

import java.util.Date;

public class UserValidaterTest {
    @Test
    public void test(){
        System.out.println("用户_"+new Date().getTime());
//        System.out.println(UserValidater.passwdValidate("h12551251"));
    }
    @Test
    public void phonetest(){
        System.out.println(UserValidater.phoneValidate("1254845284"));
    }

}