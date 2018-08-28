package com.huanxi.good.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huanxi.common.utils.PageResult;
import com.huanxi.dao.GoodMapper;
import com.huanxi.dao.GoodTypeMapper;
import com.huanxi.dao.ImageMapper;
import com.huanxi.good.service.GoodService;
import com.huanxi.pojo.Good;
import com.huanxi.pojo.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 商品服务实现类
 *
 */
@Component
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private GoodTypeMapper goodTypeMapper;
    @Override
    public PageResult getGoods(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Good> goods= (Page<Good>) goodMapper.selectByExample(null);
        return new PageResult(goods.getTotal(),goods.getResult() ,goods.getPageNum() );
    }

    @Override
    public List<Image> getGoodImg(Integer goodId) {
        return null;
    }

    @Override
    public int addGood(Good good, String[] imgSrc, Long user_id) {
        return 0;
    }
}
