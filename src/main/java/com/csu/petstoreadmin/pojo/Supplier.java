package com.csu.petstoreadmin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@TableName("supplier")
public class Supplier implements Serializable {

    @TableId(
            type = IdType.AUTO
    )
    private int suppId;
    private String name;
    private String status;
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String zip;
    private String phone;
    @TableField(
            exist = false
    )
    private String supplierAccountName;
    @TableField(
            exist = false
    )
    private String password;
    @TableField(
            exist = false
    )
    private String email;
}
