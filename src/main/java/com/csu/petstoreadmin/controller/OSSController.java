package com.csu.petstoreadmin.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.csu.petstoreadmin.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/oss")
public class OSSController {

    @Value("${oss.accessId}")
    private String accessId;
    @Value("${oss.accessKey}")
    private String accessKey;
    @Value("${oss.endpoint}")
    private String endpoint;
    @Value("${oss.bucket}")
    private String bucket;
    @Value("${oss.host}")
    private String host;
    @Value("${oss.accessId}")
    private String dir;



    @CrossOrigin
    @GetMapping("/policy")
    public Result<?> policy(){
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessId = "LTAI5tJeYSKYyWpbj4yszafK";
//        String accessKey = "ouCQpiD9bVqSk5P3We1OnC1QsA4gci";
//        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//        String endpoint = "oss-cn-guangzhou.aliyuncs.com";
//        // 填写Bucket名称，例如examplebucket。
//        String bucket = "pstore-eyu104";
//        // 填写Host地址，格式为https://bucketname.endpoint。
//        String host = "https://pstore-eyu104.oss-cn-guangzhou.aliyuncs.com";
//        // 设置上传回调URL，即回调服务器地址，用于处理应用服务器与OSS之间的通信。OSS会在文件上传完成后，把文件上传信息通过此回调URL发送给应用服务器。
////        String callbackUrl = "https://192.168.0.0:8888";
////        // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
//        String dir = "product/";

        // 创建ossClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        try {
            long expireTime = 600;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessId", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));

            return Result.success(respMap);
        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        }

        return null;
    }




    
}
