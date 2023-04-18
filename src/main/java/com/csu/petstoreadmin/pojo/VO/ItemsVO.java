package com.csu.petstoreadmin.pojo.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.csu.petstoreadmin.pojo.Product;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
public class ItemsVO{
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
    private String category;
    private String name;
    private String descn;
}
