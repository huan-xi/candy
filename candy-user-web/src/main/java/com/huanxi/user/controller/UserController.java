package com.huanxi.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huanxi.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import user.service.UserService;
import com.huanxi.common.message.ReturnMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Reference()
    private UserService userService;
    private User user;
    public UserController() {
        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        this.user=userService.findUserByPhone(account);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ReturnMessage register(HttpServletRequest request, User user, String submitCaptcha) {
        //验证session中图片验证码
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (captcha != null && captcha.equals(submitCaptcha)) return new ReturnMessage(1003, "验证码错误");
        //短信验证码

        //开始注册
        int status = userService.fastReg(user);
        //返回错误代码
        String msg;
        switch (status) {
            case 1:
                msg = "注册成功";
                break;
            case 1000:
                msg = "密码格式错误";
                break;
            case 1001:
                msg = "手机号格式错误";
                break;
            case 1002:
                msg = "该手机号已注册";
                break;
            default:
                status = 0;
                msg = "未知错误";
        }
        return new ReturnMessage(status, msg);
    }

    /**
     * 获取根据账号用户信息
     *
     * @return
     */
    @RequestMapping("/getInfo")
    public User getInfo() {
        return this.user;
    }
    @RequestMapping("/updateInfo")
    public ReturnMessage updateInfo(User IUser) {
        userService.update(IUser);
        return new ReturnMessage(1, "修改成功");
    }

}
