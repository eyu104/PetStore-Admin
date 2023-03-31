package com.csu.petstoreadmin;

import com.csu.petstoreadmin.pojo.Supplier;
import com.csu.petstoreadmin.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class PetStoreAdminApplicationTests {

    @Autowired
    SupplierService service;

    @Test
    void contextLoads() {
        Supplier supplier = service.getSupByAccountNameAndPassword("ABC123","666666");
        System.out.println(supplier);
    }

}
