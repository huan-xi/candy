package com.huanxi.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Reference()
    private UserService userService;
    @RequestMapping("/getUserInfo")
    public String getUserInfo(){
        return  userService.getUserInfo();
    }
}
