package com.huanxi.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.huanxi.user.service.UserService;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0",timeout = 10000)
public class UserServiceImpl implements UserService {
    @Override
    public String getUserInfo() {
        return "userinfo";
    }
}
