package com.csu.petstoreadmin.mapper;

import com.csu.petstoreadmin.pojo.Item;
import com.csu.petstoreadmin.pojo.LineItem;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemMapper extends MPJBaseMapper<Item> {

    @Select("SELECT t.* " +
            "from lineitem t " +
            "LEFT JOIN item t1 on t1.itemid = t.itemid " +
            "WHERE t.orderid = #{orderid} and " +
            "t1.supplier = #{supplier}")
    List<LineItem>  getLineItemsByOrderidAndSupplier(int orderid,int supplier);

}
