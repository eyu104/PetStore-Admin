package com.csu.petstoreadmin.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 3992469837058393712L;//序列化id


    private String catid;
    private String name;
    private String descn;



}
