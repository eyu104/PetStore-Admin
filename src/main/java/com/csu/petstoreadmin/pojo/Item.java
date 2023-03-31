package com.csu.petstoreadmin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("item")
public class Item implements Serializable {

    private static final long serialVersionUID = -2159121673445254631L;
    @TableId
    private String itemId;
    private String productId;
    private BigDecimal listPrice;
    private BigDecimal unitCost;
    private int supplier;
    private String status;
    private String attr1;
    private String attr2;
    private String attr3;
    private String attr4;
    private String attr5;
    @TableField(exist = false)
    private Product product;
    @TableField(exist = false)
    private int quantity;


}
