package com.csu.petstoreadmin.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("inventory")
public class Inventory implements Serializable {
    @TableId
    private String itemId;
    private int qty;
}
