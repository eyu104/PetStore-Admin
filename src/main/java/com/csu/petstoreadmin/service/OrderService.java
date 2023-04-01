package com.csu.petstoreadmin.service;

import com.csu.petstoreadmin.mapper.OrderMapper;
import com.csu.petstoreadmin.pojo.Item;
import com.csu.petstoreadmin.pojo.LineItem;
import com.csu.petstoreadmin.pojo.Order;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private ItemService itemService;

    public void get(){
        List<Order> orders = orderMapper.selectList(Order.class,new MPJLambdaWrapper<Order>()
                .selectAll(Order.class)
                .leftJoin(LineItem.class,LineItem::getOrderId,Order::getOrderId)
                .rightJoin(Item.class,Item::getItemId,LineItem::getItemId)
                .eq(Item::getSupplier,1)
        );
        System.out.println(orders);
    }

    public List<Order> getOrderBySupplier(int supplier) {
        List<Order> list = orderMapper.get(supplier);
        for (Order order:list) {
            if (order == null){
                list.remove(order);
            }

        }

        for (Order o :list) {
            o.setLineItems(itemService.getLineItemsByOrderidAndSupplier(o.getOrderId(),supplier));
            o.getTotal();
        }

        //去重
        for (int i = 0; i < list.size();i++){
            for (int j = i + 1;j < list.size();j++){
                if (list.get(i).getOrderId() == list.get(j).getOrderId()){
                    list.remove(j);
                }
            }
        }

        return list;
    }


    public void update(Order order){
        orderMapper.updateById(order);
    }

    public void Shipment(int orderid){
        orderMapper.Shipment(orderid,"Q");
    }

    public void deleteOrder(int orderId){
        orderMapper.deleteOrder(orderId);
    }


}
