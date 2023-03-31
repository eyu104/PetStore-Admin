package com.csu.petstoreadmin.controller;

import com.csu.petstoreadmin.common.Result;
import com.csu.petstoreadmin.pojo.Supplier;
import com.csu.petstoreadmin.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;


    /**
     * 注册
     * @param supplier
     * @return
     */
    @PostMapping("/add")
    public Result<?> register(@RequestBody Supplier supplier){
        if (supplierService.getSupByAccountName(supplier.getSupplierAccountName()) != null){
            return Result.error("404","账号已存在");
        }
        else {
            supplierService.addSup(supplier);
        }
        return Result.success(supplierService.getSupByAccountName(supplier.getSupplierAccountName()));
    }

    /**
     * 登录
     * @param supplierAccountName
     * @param password
     * @return
     */
    @GetMapping("/login")
    public Result<?> login(@RequestParam String supplierAccountName,@RequestParam String password) {
        Supplier supplier = supplierService.getSupByAccountNameAndPassword(supplierAccountName,password);
        if (supplier != null){
            return Result.success(supplierService.getSupByAccountName(supplierAccountName));
        }else {
            return Result.error("404","账号或密码错误");
        }
    }

    /**
     * 修改店家信息
     * @param supplier
     * @return
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody Supplier supplier){
        supplierService.updateSup(supplier);
        return Result.success(supplierService.getSupByAccountName(supplier.getSupplierAccountName()));
    }


}
