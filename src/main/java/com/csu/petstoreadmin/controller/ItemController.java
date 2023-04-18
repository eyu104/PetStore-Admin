package com.csu.petstoreadmin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csu.petstoreadmin.common.Result;
import com.csu.petstoreadmin.mapper.ItemsMapper;
import com.csu.petstoreadmin.pojo.*;
import com.csu.petstoreadmin.pojo.VO.ItemsVO;
import com.csu.petstoreadmin.service.CategoryService;
import com.csu.petstoreadmin.service.ItemService;
import com.csu.petstoreadmin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Resource
    private ItemsMapper itemsMapper;
    /**
     * 新增商品
     * @param addItemForm
     * @return
     */
    @PostMapping("/item/add")
    public Result<?> addItem(@RequestBody AddItemForm addItemForm){
        Item item = new Item();
        item.setItemId(addItemForm.getCommodityId());
        item.setProductId(addItemForm.getProductId());
        item.setListPrice(addItemForm.getPrice());
        item.setUnitCost(addItemForm.getCost());
        item.setSupplier(addItemForm.getSupplier());
        item.setAttr1(addItemForm.getDescribe2());

        if (itemService.itemIsAlive(addItemForm.getCommodityId()) == false){
            return Result.error("404","商品名已存在");
        }

        Product product = new Product();
        product.setProductId(addItemForm.getProductId());
        product.setCategory(addItemForm.getCategory());
        product.setName(addItemForm.getName());
        product.setDescn( "\""+ addItemForm.getFileName() + "\">"+ addItemForm.getDescribe());
        itemService.addItem(item,product);
        return Result.success("新增成功");
    }

    /**
     * 通过商品id删除商品
     * @param itemId
     * @return
     */
    @DeleteMapping("item/delete")
    public Result<?> deleteItem(@RequestParam String itemId){
        itemService.deleteItem(itemId);
        return Result.success("删除成功");
    }


    /**
     * 上传图片，返回文件名
     * @param file
     * @return
     */
    @PostMapping("/image/upload")
    public Result<?> imageUpload(@RequestParam(value = "file",required = false)MultipartFile file){
        if (file.isEmpty()){
            return Result.error("404","上传图片为空");
        }
        String originalFilename = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "." + originalFilename.substring(originalFilename.lastIndexOf("." + 1));

        String path = "D:\\Javatest\\gitStore\\mypetstore-view22\\src\\assets\\images";
        File dest = new File(path+fileName);
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return Result.success(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("404","保存失败");
        }
    }

    /**
     * 增加宠物种类
     * @param cateForm
     * @return
     */
    @PostMapping("category/add")
    public Result<?> addCate(@RequestBody CateForm cateForm){
        Category category = new Category();
        category.setCatid(category.getCatid());
        category.setName(cateForm.getName());
        category.setDescn(cateForm.getFileName());
        categoryService.addCate(category);
        return Result.success();
    }

    /**
     * 获取所有宠物种类
     * @return
     */
    @GetMapping("/category/get")
    public Result<?> getCate(){
        List<Category> categoryList = categoryService.getCate();
        System.out.println(categoryList);
        return Result.success(categoryList);
    }


    /**
     * 更新商品
     * @param addItemForm
     * @return
     */
    @PostMapping("/item/update")
    public Result<?> updateItem(@RequestBody AddItemForm addItemForm){
        Item item = new Item();
        item.setItemId(addItemForm.getCommodityId());
        item.setProductId(addItemForm.getProductId());
        item.setListPrice(addItemForm.getPrice());
        item.setUnitCost(addItemForm.getCost());
        item.setSupplier(addItemForm.getSupplier());
        item.setQuantity(addItemForm.getQuantity());
        item.setAttr1(addItemForm.getDescribe2());

        itemService.updateItem(item);
        return Result.success("新增成功");
    }


    /**
     * 用于搜索产品自动补全
     * @param search
     * @return
     */
    @GetMapping("/poduct/search")
    public Result<?> getPro(@RequestParam(defaultValue = "") String search){
        List<Product> products = productService.getProduct(search);
        System.out.println(products);
        return Result.success(products);
    }

    /**
     * 在自动补全框中点击选择产品，显示产品商品
     * @param pageNum 分页数
     * @param pageSize 页面大小
     * @param search 关键词
     * @return
     */
    @GetMapping("/item/get")
    public Result<?> getItems(@RequestParam(defaultValue = "1") Integer pageNum,
                @RequestParam(defaultValue = "10") Integer pageSize,
                @RequestParam(defaultValue = "") String search,
        @RequestParam int supplier){
            Page<Item> page = itemService.getItems(pageNum,pageSize,search,supplier);
            System.out.println(page.getRecords());
            return Result.success(page);
    }

    @GetMapping("/items/get")
    public Result<?> getItems1(@RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               @RequestParam(defaultValue = "") String search,
                               @RequestParam int supplier) {

        QueryWrapper<ItemsVO> wrapper = new QueryWrapper<>();
        String s = "RT";
        wrapper.like("t1.productid",search)
                .eq("supplier",supplier);
        Page<ItemsVO> page = new Page<>(pageNum,pageSize);
        IPage<ItemsVO> itemsVOIPage = itemsMapper.get(page, supplier,wrapper);
        return Result.success(itemsVOIPage);
    }



}
