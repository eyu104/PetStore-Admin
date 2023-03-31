package com.csu.petstoreadmin.service;

import com.csu.petstoreadmin.mapper.CategoryMapper;
import com.csu.petstoreadmin.pojo.Category;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    public void addCate(Category category){
        categoryMapper.insert(category);
    }

    public List<Category> getCate(){
        List<Category> categoryList = categoryMapper.selectList(null);
        return categoryList;
    }

}
