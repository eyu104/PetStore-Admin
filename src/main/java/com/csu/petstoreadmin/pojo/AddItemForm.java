package com.csu.petstoreadmin.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddItemForm {
    private String commodityId;
    private String productId;
    private String category;
    private String name;
    private String describe;
    private String describe2;
    private int quantity;
    private int supplier;
    private String status;
    private BigDecimal price;
    private BigDecimal cost;
    //图片名称
    private String fileName;

}
