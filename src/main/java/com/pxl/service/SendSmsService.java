package com.pxl.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SendSmsService {

    /**
     * 用于发送短信
     * @param phoneNumber:手机号
     * @param templateCode:模板编号
     * @param code:验证码
     * @return 是否发送成功
     */
    public boolean send(String phoneNumber, String templateCode, Map<String,Object> code);
}
