package com.csu.petstoreadmin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@TableName("supsignon")
public class SupSignon implements Serializable {

    @TableId(
            type = IdType.AUTO
    )
    private int suppId;
    private String supplierAccountName;
    private String password;
    private String email;
}
