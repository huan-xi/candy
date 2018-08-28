package com.huanxi.dao;

import com.huanxi.pojo.GoodType;
import com.huanxi.pojo.GoodTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodTypeMapper {
    long countByExample(GoodTypeExample example);

    int deleteByExample(GoodTypeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(GoodType record);

    int insertSelective(GoodType record);

    List<GoodType> selectByExample(GoodTypeExample example);

    GoodType selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") GoodType record, @Param("example") GoodTypeExample example);

    int updateByExample(@Param("record") GoodType record, @Param("example") GoodTypeExample example);

    int updateByPrimaryKeySelective(GoodType record);

    int updateByPrimaryKey(GoodType record);
}