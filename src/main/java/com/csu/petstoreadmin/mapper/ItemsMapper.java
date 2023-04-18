package com.csu.petstoreadmin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.csu.petstoreadmin.pojo.Item;
import com.csu.petstoreadmin.pojo.VO.ItemsVO;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemsMapper extends MPJBaseMapper<Item> {

    @Select("SELECT t.*,t1.*,t2.qty as quantity " +
            "FROM item t " +
            "LEFT JOIN product t1 ON t.productid = t1.productid " +
            "LEFT JOIN inventory t2 ON t.itemid = t2.itemid " +
            "${ew.customSqlSegment}")
    IPage<ItemsVO> get(IPage<ItemsVO> page, int supplier,@Param(Constants.WRAPPER) Wrapper<ItemsVO> ew);
}
