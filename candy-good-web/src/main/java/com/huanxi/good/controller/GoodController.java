package com.huanxi.good.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.huanxi.common.utils.PageResult;
import com.huanxi.good.service.GoodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/good")
public class GoodController {
    @Reference
    private GoodService goodService;

    @RequestMapping("getGoods")
    public PageResult getGoods(Integer pageNum,Integer pageSize) {

        return goodService.getGoods(pageNum,pageSize);
    }
}
