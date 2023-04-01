package com.csu.petstoreadmin.mapper;

import com.csu.petstoreadmin.pojo.Order;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper extends MPJBaseMapper<Order> {
    List<Order> selectList(Class<Order> orderClass, MPJLambdaWrapper<Order> rightJoin);


    @Select(
            "select t.* ,t3.status as status " +
            "from orders t " +
            "LEFT JOIN lineitem t1 on t.orderid = t1.orderid " +
            "RIGHT JOIN item t2 on t1.itemid = t2.itemid " +
            "INNER JOIN orderstatus t3 on t3.orderid = t.orderid " +
            "where t2.supplier = #{supplier}")
    List<Order> get(int supplier);


    @Update("update orderstatus set status = #{status} where orderid = #{orderid}")
    void Shipment(int orderid,String status);

    @Delete("DELETE a,b,c " +
            "from orders and " +
            "LEFT JOIN lineitem b on b.orderid = a.orderid " +
            "LEFT JOIN orderstatus c on c.orderid = a.orderid " +
            "where a.orderid = #{orderid}")
    void deleteOrder(int orderid);





}
