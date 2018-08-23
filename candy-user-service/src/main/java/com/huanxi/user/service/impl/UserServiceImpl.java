package com.huanxi.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.huanxi.dao.UserMapper;
import com.huanxi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import user.service.UserService;

@Component
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User findUserById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public User findUserByPhone(String phone) {
        return null;
    }
}
