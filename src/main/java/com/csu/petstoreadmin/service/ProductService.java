package com.csu.petstoreadmin.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csu.petstoreadmin.mapper.ProductMapper;
import com.csu.petstoreadmin.pojo.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Resource
    private ProductMapper productMapper;

    public List<Product> getProduct(String search){
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Product::getName,search);
        List<Product> products = productMapper.selectList(wrapper);
        System.out.println(products);
        return products;
    }


}
