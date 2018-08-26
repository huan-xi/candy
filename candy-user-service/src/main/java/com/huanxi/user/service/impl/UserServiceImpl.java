package com.huanxi.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.huanxi.common.message.com.huanxi.common.validate.UserValidater;
import com.huanxi.dao.UserMapper;
import com.huanxi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import user.service.UserService;

import java.util.Date;

@Component
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int fastReg(User user) {
        //设置默认信息
        user.setRegTime(new Date());
        user.setUsername("用户_t"+new Date().getTime());
        user.setStatus("1");
        //加密码
        if(!UserValidater.passwdValidate(user.getPassword()))return 1000;
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        //手机号格式验证
        if(!UserValidater.phoneValidate(user.getPhone()))return 1001;
        //检查重复
        if (userMapper.findUserByPhone(user.getPhone())!=null)
            return 1002;
        userMapper.insert(user);
        return 1;
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
        return null;
    }

    @Override
    public User findUserByPhone(String phone) {
        return userMapper.findUserByPhone(phone);
    }
}
