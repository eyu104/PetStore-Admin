package com.csu.petstoreadmin;

import com.csu.petstoreadmin.pojo.Supplier;
import com.csu.petstoreadmin.service.ItemService;
import com.csu.petstoreadmin.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PetStoreAdminApplicationTests {

    @Autowired
    SupplierService service;

    @Autowired
    ItemService itemService;

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

}
