package com.csu.petstoreadmin.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csu.petstoreadmin.mapper.InventoryMapper;
import com.csu.petstoreadmin.mapper.ItemMapper;
import com.csu.petstoreadmin.mapper.ProductMapper;
import com.csu.petstoreadmin.pojo.Inventory;
import com.csu.petstoreadmin.pojo.Item;
import com.csu.petstoreadmin.pojo.LineItem;
import com.csu.petstoreadmin.pojo.Product;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemService {

    @Resource
    private InventoryMapper inventoryMapper;

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private ProductMapper productMapper;

    public void addItem(Item item, Product product){
        Inventory inventory = new Inventory();
        inventory.setItemId(item.getItemId());
        inventory.setQty(item.getQuantity());
        item.setAttr2("");
        item.setAttr3("");
        item.setAttr4("");
        item.setAttr5("");

        productMapper.insert(product);
        itemMapper.insert(item);
        inventoryMapper.insert(inventory);

    }

    public void deleteItem(String itemid){
        QueryWrapper<Item> wrapper = new QueryWrapper<>();
        wrapper.eq("itemid",itemid);
        itemMapper.delete(wrapper);
    }

    public void updateItem(Item item){
        Inventory inventory = new Inventory();
        inventory.setItemId(item.getItemId());
        inventory.setQty(item.getQuantity());
        itemMapper.updateById(item);
        inventoryMapper.updateById(inventory);
    }

    /**
     * 用于登录后根据商家id获得所有拥有商品
     * 也可用于点击产品自动补全项后，搜索展示所有对应商品
     * @param pageNum
     * @param pageSize
     * @param search
     * @param supplierId
     * @return
     */
    public Page<Item> getItems(Integer pageNum, Integer pageSize, String search,int supplierId) {
        LambdaQueryWrapper<Item> wrapper = new LambdaQueryWrapper();
            wrapper.like(Item::getProductId,search)
                    .eq(Item::getSupplier,supplierId);
        Page<Item> itemPage = itemMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        System.out.println(itemPage);
        return itemPage;
    }

    public boolean itemIsAlive(String itemid) {
        Item item = itemMapper.selectById(itemid);
        if (item != null){
            return false;
        }
        else {
            return true;
        }
    }

    public List<LineItem> getLineItemsByOrderidAndSupplier(int orderid,int supplier){
        List<LineItem> list = itemMapper.getLineItemsByOrderidAndSupplier(orderid,supplier);

        for (LineItem i: list) {
            Item item = itemMapper.selectById(i);
            item.setProduct(productMapper.selectById(item));
            i.setItem(item);
            i.calculateTotal();
        }

        return list;

    }


}
