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
public class UserController {
    @Reference()
    private UserService userService;

    private User getUser() {
        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByPhone(account);
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

    @RequestMapping("/checkCaptcha")
    public ReturnMessage checkCaptcha(HttpServletRequest request,String captcha){
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
        User user=getUser();
        user.setPassword(null);
        return user;
    }

    @RequestMapping("/updateInfo")
    public ReturnMessage updateInfo(User user) {
        user.setUserId(getUser().getUserId());
        if (userService.update(user)!=0) return new ReturnMessage(1, "修改成功");
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
            User user = getUser();
            if (user == null) return new ReturnMessage(1001, "上传失败");
            user.setHeadImg(ossFile);
            userService.update(user);
            return new ReturnMessage(1, "上传成功");
        }
        return new ReturnMessage(1000, "上传失败");
    }

    /**
     * 上传商品图片
     */
    @RequestMapping("/uploadGoodImg")
    public ReturnMessage uploadGoodImg(MultipartFile head_img) {
        String filename = UUID.randomUUID().toString() + "." + head_img.getContentType().split("/")[1];
        String ossFile = null;
        try {
            ossFile = AliOSSUtil.uploadLocalFile((FileInputStream) head_img.getInputStream(), filename, "image/good_img/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!ossFile.isEmpty())
            return new ReturnMessage(1, ossFile);
        return new ReturnMessage(0, "上传失败");
    }


    /**
     * 发布商品
     */
    @RequestMapping("/public_good")
    public ReturnMessage public_good(String img_src[]) {
        User user = getUser();
        return null;
    }

}
