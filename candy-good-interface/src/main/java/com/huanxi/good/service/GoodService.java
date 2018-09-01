package com.huanxi.good.service;

import com.huanxi.common.message.ReturnMessage;
import com.huanxi.common.utils.PageResult;
import com.huanxi.pojo.Good;

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
    public List<String> getGoodImg(Integer goodId);
    /**
     * 新增商品
     *
     * @param good
     * @param imgSrc  必须有图片
     * @param user_id
     * @return
     */
    public ReturnMessage addGood(Good good, String imgSrc[], Long user_id);

    /**
     * 获取商品的评论
     * @param goodId
     */
    public void getGoodCommon(Integer goodId);
    /**
     * 评论商品
     * @param goodId
     * @param common
     * @return
     */
    public int CommonGood(Integer goodId,String common);

    /**
     * 添加商品分类
     * @return
     */
    public int addType(String s);
}
