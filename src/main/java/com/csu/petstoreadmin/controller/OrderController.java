package com.csu.petstoreadmin.controller;

import com.csu.petstoreadmin.common.Result;
import com.csu.petstoreadmin.pojo.Order;
import com.csu.petstoreadmin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据登录卖家的id获取订单
     * @param supplierId
     * @return
     */
    @GetMapping("/get")
    public Result<?> getOrderBySupplier(@RequestParam int supplierId){
        List<Order> list = orderService.getOrderBySupplier(supplierId);
        return Result.success(list);
    }

    /**
     * 根据订单号发货
     * @param orderId
     * @return
     */
    @PostMapping("/ship")
    public Result<?> shipment(@RequestParam int orderId) {
        orderService.Shipment(orderId);
        return Result.success();
    }

    /**
     * 更新订单信息，前端重新获得信息加载
     * @param order
     * @return
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody Order order){
        orderService.update(order);
        return Result.success("更新成功");
    }




}
