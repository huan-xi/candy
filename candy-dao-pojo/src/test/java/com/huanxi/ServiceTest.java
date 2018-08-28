package com.huanxi;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huanxi.candydaopojo.GoodServiceImpl;
import com.huanxi.common.message.ReturnMessage;
import com.huanxi.common.utils.PageResult;
import com.huanxi.dao.GoodMapper;
import com.huanxi.dao.GoodTypeMapper;
import com.huanxi.dao.ImageMapper;
import com.huanxi.dao.UserMapper;
import com.huanxi.pojo.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class ServiceTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext-dao.xml");
    UserMapper userMapper = (UserMapper) context.getBean("userMapper");
    GoodTypeMapper goodTypeMapper = (GoodTypeMapper) context.getBean("goodTypeMapper");
    GoodMapper goodMapper = (GoodMapper) context.getBean("goodMapper");
    ImageMapper imageMapper= (ImageMapper) context.getBean("imageMapper");

    GoodServiceImpl goodService=new GoodServiceImpl();
    @Test
    public void update() {
        User user=new User();
        user.setUserId(1L);
        user.setUsername("huanxi");
        //设置不让更新的字段
        user.setStatus(null);
        user.setPassword(null);
        user.setPhone(null);
        user.setRegTime(null);
        System.out.println(userMapper.updateByPrimaryKeySelective(user));
    }
    @Test
    public void findCountByphone() {
        UserExample userExample = new UserExample();
        System.out.println(userExample.createCriteria().andPhoneEqualTo("17680504804"));
        System.out.println(userMapper.countByExample(userExample));
        System.out.println(userMapper.selectByExample(userExample));
    }
    /**
     * 新增商品
     *
     * @param good
     * @param imgSrc  必须有图片
     * @param user_id
     * @return
     */
    public int addGood(Good good, String imgSrc[], Long user_id) {
        if (imgSrc.length == 0) return 1000;
        if (good.getTypeId() == null) return 1001;
        if (good.getTitle() == null) return 1002;
        if (good.getGoodDesc() == null) return 1002;
        goodMapper.insert(good);
        //设置用户
        good.setUserId(user_id);
        good.setStatus("1");
        good.setCreateTime(new Date().getTime());
        goodMapper.insert(good);
        for (String src : imgSrc) {
            imageMapper.insert(new Image(good.getGoodId(),src));
        }
        return 1;
    }
    @Test
    public void test() {
        getGoods(1,10 );
//        for(int i=0;i<20;i++)
//            addGood();
    }

    @Test
    public void insertType() {
        GoodType type = new GoodType();
        type.setTitle("其他");
        GoodTypeExample goodTypeExample = new GoodTypeExample();
        goodTypeExample.createCriteria().andTitleEqualTo(type.getTitle());
        if (goodTypeMapper.countByExample(goodTypeExample) != 0)
            return;
        goodTypeMapper.insert(type);
    }

    @Test
    public void addGood() {
        Good good = new Good();
        good.setUserId(1L);
        good.setTitle("iphone");
        good.setTypeId(1);
        good.setGoodDesc("这是一台苹果手机");
        String[] imgSrc = {"fdsa", "fdas"};
        System.out.println(addGood(good, imgSrc, 1L));
    }
    /**
     * 获取商品图片
     */
    public List<Image> getGoodImg(Integer goodID){
        ImageExample imageExample=new ImageExample();
        imageExample.createCriteria().andGoodIdEqualTo(goodID);
        return imageMapper.selectByExample(imageExample);
    }
    /**
     * 获取用户发布的商品
     */
    public List<Good> getGoodOfUser(long userId){
        GoodExample example=new GoodExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return goodMapper.selectByExample(example);
    }
    /**
     * 浏览商品
     */
    public PageResult getGoods(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<Good> goods= (Page<Good>) goodMapper.selectByExample(null);
        return new PageResult(goods.getTotal(),goods.getResult() ,goods.getPageNum() );
    }
}