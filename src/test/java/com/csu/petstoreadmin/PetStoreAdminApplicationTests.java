package com.csu.petstoreadmin;

import com.csu.petstoreadmin.mapper.ItemMapper;
import com.csu.petstoreadmin.mapper.OrderMapper;
import com.csu.petstoreadmin.pojo.Item;
import com.csu.petstoreadmin.pojo.LineItem;
import com.csu.petstoreadmin.pojo.Order;
import com.csu.petstoreadmin.pojo.Supplier;
import com.csu.petstoreadmin.service.ItemService;
import com.csu.petstoreadmin.service.OrderService;
import com.csu.petstoreadmin.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class PetStoreAdminApplicationTests {

    @Autowired
    SupplierService service;

    @Autowired
    ItemService itemService;

    @Resource
    ItemMapper itemMapper;
    @Resource
    OrderMapper orderMapper;

    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
        Supplier supplier = service.getSupByAccountNameAndPassword("ABC123","666666");
        System.out.println(supplier);
    }

    @Test
    void test() {
        itemService.deleteItem("123");
    }

    @Test
    void test3() {
        System.out.println(itemService.itemIsAlive("EST-1"));
    }

    @Test
    void test4() {
        List<Order> orders = orderMapper.get(1);
        for (Order order:
             orders) {
            if (order != null)
            System.out.println(order.getOrderId() +"   " + order.getOrderDate() + " " + order.getUserId());
        }

    }

    @Test
    void test5(){
        List<LineItem> list = itemMapper.getLineItemsByOrderidAndSupplier(1074,1);
        for (LineItem i :
                list) {
            System.out.println(i.getOrderId() +"  " + i.getItemId() + " " + i.getQuantity());
        }
    }

    @Test
    void test6(){
        List<Order> orders = orderService.getOrderBySupplier(1);

        for (Order o:
             orders) {
            for (LineItem i:o.getLineItems()) {
                System.out.println(o.getOrderId() + "  "+ i.getItemId());
            }
        }
    }

    @Test
    void test7(){
        String in = "1245";
        String sea = "124";
        boolean i = in.matches("(.*)"+sea+"(.*)");
        boolean j = in.matches(sea+"(.*)");
        boolean k = in.matches("(.*)"+sea);
        boolean l = in.matches(sea);
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
        System.out.println(l);

    }

    @Test
    void test9() {
        Date date = new Date();
        Order order = new Order();
        order.setOrderId(1000);
        order.setUserId("hahaha");
        order.setOrderDate(date);
        order.setBillAddr1("hhhh");
        order.setShipCity("biejing");
        order.setShipState("ll");
        order.setShipZip("1234");
        order.setShipCountry("usa");
        order.setShipAddr1("beijing");
        order.setBillCity("nn");
        order.setBillState("lll");
        order.setBillCountry("UK");
        order.setCourier("nis");

        orderMapper.updateById(order);
    }

}
