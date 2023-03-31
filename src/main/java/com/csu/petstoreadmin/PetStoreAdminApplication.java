package com.csu.petstoreadmin;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = {"com.csu.petstoreadmin.mapper"})
//@EnableSwagger2
public class PetStoreAdminApplication {


    public static void main(String[] args) {
        SpringApplication.run(PetStoreAdminApplication.class, args);
    }

}
