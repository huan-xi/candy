package com.huanxi.candydaopojo;

import com.huanxi.dao.GoodMapper;
import com.huanxi.dao.GoodTypeMapper;
import com.huanxi.dao.ImageMapper;
import com.huanxi.dao.UserMapper;
import com.huanxi.good.service.GoodService;
import com.huanxi.good.service.impl.GoodServiceImpl;
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
        System.out.println(goodService.getGoods(1,10 ));
    }

    @Test
    public void getGoodImg() {
    }

    @Test
    public void addGood() {

    }

    @Test
    public void getGoodCommon() {
    }

    @Test
    public void commonGood() {
    }
}