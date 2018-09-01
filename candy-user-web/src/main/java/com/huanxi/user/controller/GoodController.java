package com.huanxi.user.controller;

import com.huanxi.common.message.ReturnMessage;
import com.huanxi.oss.AliOSSUtil;
import com.huanxi.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 用户操作商品
 */
@Controller
public class GoodController extends UserController{
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
        return null;
    }
    /**
     * 查看已购买商品
     */

    /**
     * 查看发布的商品
     */
}
