package com.csu.petstoreadmin.controller;

import com.csu.petstoreadmin.common.Result;
import com.csu.petstoreadmin.pojo.AddItemForm;
import com.csu.petstoreadmin.pojo.Item;
import com.csu.petstoreadmin.pojo.Product;
import com.csu.petstoreadmin.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/item/add")
    public Result<?> addItem(@RequestBody AddItemForm addItemForm){
        Item item = new Item();
        item.setItemId(addItemForm.getCommodityId());
        item.setProductId(addItemForm.getProductId());
        item.setListPrice(addItemForm.getPrice());
        item.setUnitCost(addItemForm.getCost());
        item.setSupplierId(addItemForm.getSupplier());
        item.setAttribute1(addItemForm.getDescribe());

        Product product = new Product();
        product.setProductId(addItemForm.getProductId());
        product.setCategoryId(addItemForm.getCategory());
        product.setName(addItemForm.getName());
        product.setDescription(addItemForm.getDescribe());
        itemService.addItem(item,product);
        return Result.success("新增成功");
    }


}
