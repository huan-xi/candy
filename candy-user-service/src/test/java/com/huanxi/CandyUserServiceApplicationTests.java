package com.huanxi;

import com.huanxi.user.CandyUserServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.huanxi.user.service.impl.UserServiceImpl;
import user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CandyUserServiceApplication.class)
public class CandyUserServiceApplicationTests {
    @Autowired
    UserService userService;

    @Test
    public void contextLoads() {
        userService.findUserById(2L);
    }

}
