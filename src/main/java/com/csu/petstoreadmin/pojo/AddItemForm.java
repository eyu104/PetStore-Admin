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
    private int supplier;
    private String status;
    private BigDecimal price;
    private BigDecimal cost;
    //图片暂定

}
