package com.csu.petstoreadmin.service;

import com.csu.petstoreadmin.mapper.SupplierMapper;
import com.csu.petstoreadmin.pojo.SupSignon;
import com.csu.petstoreadmin.pojo.Supplier;
import com.github.yulichang.interfaces.MPJBaseJoin;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SupplierService {



    @Resource
    private SupplierMapper supplierMapper;

    public Supplier getSupByAccountNameAndPassword(String supplierAccountName, String password) {
        Supplier supplier = supplierMapper.selectJoinOne(Supplier.class, new MPJLambdaWrapper<Supplier>()
                .selectAll(Supplier.class)
                .selectAs(SupSignon::getSupplierAccountName, Supplier::getSupplierAccountName)
                .selectAs(SupSignon::getEmail, Supplier::getEmail)
                .selectAs(SupSignon::getPassword, Supplier::getPassword)
                .leftJoin(SupSignon.class, SupSignon::getSuppId, Supplier::getSuppId)
                .eq(SupSignon::getSupplierAccountName, supplierAccountName)
                .eq(SupSignon::getPassword, password));
        return supplier;
    }

    public Supplier getSupByAccountName(String supplierAccountName){
        Supplier supplier = supplierMapper.selectJoinOne(Supplier.class, new MPJLambdaWrapper<Supplier>()
                .selectAll(Supplier.class)
                .selectAs(SupSignon::getSupplierAccountName, Supplier::getSupplierAccountName)
                .selectAs(SupSignon::getEmail, Supplier::getEmail)
                .selectAs(SupSignon::getPassword, Supplier::getPassword)
                .leftJoin(SupSignon.class, SupSignon::getSuppId, Supplier::getSuppId)
                .eq(SupSignon::getSupplierAccountName, supplierAccountName)
                );
        return supplier;
    }
//
//    public void updateSup(Supplier supplier);
//
//    public void addSup(Supplier supplier);
}
