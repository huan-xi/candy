package com.huanxi.user.controller;

import com.huanxi.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;
import user.service.UserService;

public class UserController {
    public User getUser(UserService userService) {
        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByPhone(account);
    }
}
