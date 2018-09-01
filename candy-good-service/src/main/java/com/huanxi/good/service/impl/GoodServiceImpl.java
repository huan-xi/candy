package com.huanxi.good.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huanxi.common.message.ReturnMessage;
import com.huanxi.common.utils.PageResult;
import com.huanxi.dao.GoodMapper;
import com.huanxi.dao.GoodTypeMapper;
import com.huanxi.dao.ImageMapper;
import com.huanxi.good.service.GoodService;
import com.huanxi.pojo.*;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品服务实现类
 */
@Component
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private ImageMapper imageMapper;

    public void setGoodMapper(GoodMapper goodMapper) {
        this.goodMapper = goodMapper;
    }

    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    public void setGoodTypeMapper(GoodTypeMapper goodTypeMapper) {
        this.goodTypeMapper = goodTypeMapper;
    }

    @Autowired
    private GoodTypeMapper goodTypeMapper;

    @Override
    public PageResult getGoods(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Good> goods = (Page<Good>) goodMapper.selectByExample(null);
        return new PageResult(goods.getTotal(), goods.getResult(), goods.getPageNum());
    }

    @Override
    public List<String> getGoodImg(Integer goodId) {
        ImageExample imageExample = new ImageExample();
        imageExample.createCriteria().andGoodIdEqualTo(goodId);
        List<Image> desc = imageMapper.selectByExample(imageExample);
        List<String> src = null;
        for (Image i : desc) {
            src.add(i.getSrc());
        }
        return src;
    }

    @Override
    public ReturnMessage addGood(Good good, String[] imgSrc, Long user_id) {
        //字段不能为空
        if (StringUtils.isEmpty(good.getImgSrc())) return new ReturnMessage(1000,"图片不能为空");
        if (good.getGoodTypeId() == null) new ReturnMessage(1001,"商品分类");
        if (StringUtils.isEmpty(good.getTitle())) new ReturnMessage(1002,"商品标题不能为空");
        if (StringUtils.isEmpty(good.getGoodDesc())) new ReturnMessage(1003,"商品描述不能为空");
        goodMapper.insert(good);
        //设置用户
        good.setUserId(user_id);
        good.setStatus("1");
        good.setCreateTime(new Date().getTime());
        goodMapper.insert(good);
        for (String src : imgSrc) {
            imageMapper.insert(new Image(good.getGoodId(), src));
        }
        return new ReturnMessage(1,"添加成功");
    }

    @Override
    public void getGoodCommon(Integer goodId) {

    }

    @Override
    public int CommonGood(Integer goodId, String common) {
        return 0;
    }

    @Override
    public int addType(String title) {
        GoodType type = new GoodType();
        type.setTitle(title);
        GoodTypeExample goodTypeExample = new GoodTypeExample();
        goodTypeExample.createCriteria().andTitleEqualTo(type.getTitle());
        if (goodTypeMapper.countByExample(goodTypeExample) != 0)
            return 1000; //分类已存在
        return goodTypeMapper.insert(type);
    }
}
