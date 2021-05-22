package com.pxl.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.pxl.service.SendSmsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SendSmsServiceImpl implements SendSmsService {

    @Override
    public boolean send(String phoneNumber, String templateCode, Map<String, Object> code) {
        // 这里的 accessKeyId 、 accessSecret 阿里云用户对于的值，复制过来即可
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
        IAcsClient client = new DefaultAcsClient(profile);

        // 构建请求信息，下面不需要改
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("AddSmsSign");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        // 自定义参数 ：
        // 手机号，这里 Value 就填用户的手机号，实际应用须要从表单获取
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        // 签名，这里的 Value 就是在阿里云上申请的 签名
        request.putQueryParameter("SignName", "小龙龙服务平台");
        // 模板，这里的 Value 就是在阿里云上申请的模板的 模版CODE 值
        request.putQueryParameter("TemplateCode", templateCode);
        // 验证码，真实应用需要自动构建验证码
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(code));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            // 自带的判断是否成功的方法
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

}
