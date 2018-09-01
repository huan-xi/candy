package com.huanxi.good.service.impl;

import com.huanxi.dao.GoodMapper;
import com.huanxi.dao.GoodTypeMapper;
import com.huanxi.dao.ImageMapper;
import com.huanxi.dao.UserMapper;
import com.huanxi.pojo.Good;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class GoodServiceImplTest {
    GoodServiceImpl goodService=new GoodServiceImpl();
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
    UserMapper userMapper = (UserMapper) context.getBean("userMapper");
    GoodTypeMapper goodTypeMapper = (GoodTypeMapper) context.getBean("goodTypeMapper");
    GoodMapper goodMapper = (GoodMapper) context.getBean("goodMapper");
    ImageMapper imageMapper = (ImageMapper) context.getBean("imageMapper");
    @Before
    public void inter(){
        goodService.setGoodMapper(goodMapper);
        goodService.setGoodTypeMapper(goodTypeMapper);
        goodService.setImageMapper(imageMapper);
    }
    @Test
    public void getGoods() {
        System.out.println(goodService.getGoods(1,10 ).getTotal());
    }

    @Test
    public void getGoodImg() {
        goodService.getGoodImg(1);
        return;
    }

    @Test
    public void addGood() {
   /*     Good good = new Good();
        good.setUserId(1L);
        good.setTitle("iphone");
        good.setGoodTypeId(1);
        good.setImgSrc("fdas");
        good.setGoodDesc("这是一台苹果手机");
        String[] imgSrc = {"fdsa", "fdas"};
        assert goodService.addGood(good, imgSrc, 1L).getStatus()==1;*/
    }

    @Test
    public void getGoodCommon() {
    }

    @Test
    public void commonGood() {
    }

    @Test
    public void addType() {
        System.out.println(goodService.addType("手机"));
    }
}