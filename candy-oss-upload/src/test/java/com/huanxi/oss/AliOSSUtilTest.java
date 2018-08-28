package com.huanxi.oss;

import java.io.File;


public class AliOSSUtilTest {

    @org.junit.Test
    public void uploadLocalFile() {
        File file=new File("C:\\Users\\huanzi\\Pictures\\springmvc.png");
        System.out.println(AliOSSUtil.uploadLocalFile(file, "image/head_img/"));
    }
}