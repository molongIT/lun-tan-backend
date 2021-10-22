package com.pxl.controller;

import com.pxl.common.annotation.AnonymousAccess;
import com.tencent.cloud.CosStsClient;
import com.tencent.cloud.Response;
import com.tencent.cloud.cos.util.Jackson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;

@RestController
@RequestMapping("/cos")
public class CosController {

    @GetMapping()
    @AnonymousAccess
    public String getAuth(){
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {
            // 替换为您的 SecretId
            config.put("SecretId", "AKIDxjGXYJn0wc19zZsuf9NK0PYR3OSGo43m");
            // 替换为您的 SecretKey
            config.put("SecretKey", "TfaFd7LIzQaT6RVJq65oFQL4arte8NM5");

            // 临时密钥有效时长，单位是秒，默认1800秒，目前主账号最长2小时（即7200秒），子账号最长36小时（即129600秒）
            config.put("durationSeconds", 7200);

            // 换成您的 bucket
            config.put("bucket", "luntan-1304511484");
            // 换成 bucket 所在地区
            config.put("region", "ap-nanjing");

            // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子：a.jpg 或者 a/* 或者 * 。
            // 如果填写了“*”，将允许用户访问所有资源；除非业务需要，否则请按照最小权限原则授予用户相应的访问权限范围。
            config.put("allowPrefixes", new String[] {
                    "*",
            });

            // 密钥的权限列表。必须在这里指定本次临时密钥所需要的权限。
            // 简单上传、表单上传和分片上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[] {
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传、小程序上传
                    "name/cos:PostObject",
                    // 分块上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);

            Response response = CosStsClient.getCredential(config);
            //成功返回临时密钥信息，如下打印密钥信息
            System.out.println(Jackson.toJsonPrettyString(response));
            return Jackson.toJsonPrettyString(response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("no valid secret !");
        }
    }
}
