package com.csu.petstoreadmin.service;

import com.csu.petstoreadmin.mapper.ItemMapper;
import com.csu.petstoreadmin.mapper.ProductMapper;
import com.csu.petstoreadmin.pojo.Item;
import com.csu.petstoreadmin.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemService {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private ProductMapper productMapper;

    public void addItem(Item item, Product product){
        itemMapper.insert(item);
        productMapper.insert(product);
    }


}
