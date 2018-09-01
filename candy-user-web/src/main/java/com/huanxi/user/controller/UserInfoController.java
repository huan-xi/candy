package com.huanxi.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huanxi.oss.AliOSSUtil;
import com.huanxi.pojo.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import user.service.UserService;
import com.huanxi.common.message.ReturnMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserInfoController extends UserController {
    @Reference()
    private UserService userService;

    @RequestMapping("/getPhoneCaptcha")
    public ReturnMessage getPhoneCaptcha(HttpServletRequest request,String captcha){
        return null;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ReturnMessage register(HttpServletRequest request, User user, String captcha) {
        //验证session中图片验证码
        HttpSession session = request.getSession();
        String GenCaptcha = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        session.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (GenCaptcha != null && GenCaptcha.equals(captcha)) return new ReturnMessage(1003, "验证码错误");
        //短信验证码
        //开始注册
        return userService.fastReg(user);
    }

    @RequestMapping("/checkCaptcha")
    public ReturnMessage checkCaptcha(HttpServletRequest request, String captcha) {
        //验证session中图片验证码
        HttpSession session = request.getSession();
        String GenCaptcha = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        if (GenCaptcha != null && GenCaptcha.equals(captcha)) return new ReturnMessage(1003, "验证码错误");
        return new ReturnMessage(1, "验证成功");
    }

    /**
     * 获取根据账号用户信息
     *
     * @return
     */
    @RequestMapping("/getInfo")
    public User getInfo() {
        User user = super.getUser(this.userService);
        user.setPassword(null);
        return user;
    }

    @RequestMapping("/updateInfo")
    public ReturnMessage updateInfo(User user) {
        user.setUserId(getUser(this.userService).getUserId());
        if (userService.update(user) != 0) return new ReturnMessage(1, "修改成功");
        return new ReturnMessage(1000, "修改失败");
    }

    /**
     * 修改头像,大小不超过5M
     *
     * @param head_img
     * @return
     */
    @RequestMapping("/updateImg")
    public ReturnMessage upload(MultipartFile head_img) {
        String filename = UUID.randomUUID().toString() + "." + head_img.getContentType().split("/")[1];
        String ossFile = null;
        try {
            ossFile = AliOSSUtil.uploadLocalFile((FileInputStream) head_img.getInputStream(), filename, "image/head_img/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!ossFile.isEmpty()) {
            User user = getUser(this.userService);
            if (user == null) return new ReturnMessage(1001, "上传失败");
            user.setHeadImg(ossFile);
            userService.update(user);
            return new ReturnMessage(1, ossFile);
        }
        return new ReturnMessage(1000, "上传失败");
    }


}
