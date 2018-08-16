package com.huanxi.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import user.service.UserService;

@Component
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String getUserInfo() {
        return "userinfo";
    }
}
