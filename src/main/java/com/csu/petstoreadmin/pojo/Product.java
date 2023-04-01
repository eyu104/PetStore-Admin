package com.csu.petstoreadmin.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = -7492639752670189553L;
    @TableId(value = "productid")
    private String productId;
    private String category;
    private String name;
    private String descn;



}
