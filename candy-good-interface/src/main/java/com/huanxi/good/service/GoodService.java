package com.huanxi.good.service;

import com.huanxi.common.utils.PageResult;
import com.huanxi.pojo.Good;
import com.huanxi.pojo.Image;

import java.util.List;

public interface GoodService {
    /**
     * 浏览商品
     */
    public PageResult getGoods(int pageNum, int pageSize);
    /**
     * 获取商品图片
     * @param goodId
     */
    public List<Image> getGoodImg(Integer goodId);
    /**
     * 新增商品
     *
     * @param good
     * @param imgSrc  必须有图片
     * @param user_id
     * @return
     */
    public int addGood(Good good, String imgSrc[], Long user_id);
}
