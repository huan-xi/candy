package com.huanxi.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.huanxi.common.message.ReturnMessage;
import com.huanxi.common.validate.UserValidater;
import com.huanxi.dao.UserMapper;
import com.huanxi.pojo.User;
import com.huanxi.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import user.service.UserService;

import java.util.Date;
import java.util.List;

@Component
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ReturnMessage fastReg(User user) {
        //设置默认信息
        user.setRegTime(new Date().getTime());
        user.setUsername("用户_t"+new Date().getTime());
        user.setStatus("1");
        //手机号格式验证
        if(!UserValidater.phoneValidate(user.getPhone()))return new ReturnMessage(1001,"手机号格式错误");
        //加密码
        if(!UserValidater.passwdValidate(user.getPassword()))return new ReturnMessage(1000,"密码格式错误");
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        //检查重复
        UserExample userExample=new UserExample();
        userExample.createCriteria().andPhoneEqualTo(user.getPhone());
        long count =userMapper.countByExample(userExample);
        if (count!=0)
            new ReturnMessage(1002,"手机号已存在");
        userMapper.insert(user);
        return new ReturnMessage(1,"注册成功");
    }

    @Override
    public int update(User user) {
        //设置不让更新的字段
        user.setStatus(null);
        user.setPassword(null);
        user.setPhone(null);
        user.setRegTime(null);
        return userMapper.updateByPrimaryKeySelective(user);
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
        UserExample userExample=new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        List<User> users=userMapper.selectByExample(userExample);
        if (users.size()!=1)return null;
        return users.get(0);
    }
}
