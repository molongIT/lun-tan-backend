package com.pxl.controller;


import com.aliyuncs.utils.StringUtils;
import com.pxl.service.SendSmsService;
import net.sf.jsqlparser.statement.select.KSQLWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
public class SmsApiController {

    @Autowired
    private SendSmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/send/{phoneNumber}")
    public String sendSms(@PathVariable String phoneNumber) {
        //整合redis，模拟真实业务

        //判断当前手机号是否存储在Redis中,如果没有的话则发送短信，如果又表示行一个验证码还未过期，不用发送。
        String code = (String) redisTemplate.opsForValue().get(phoneNumber);
        if (!StringUtils.isEmpty(code)) {
            return "[手机号: " + phoneNumber + "],[验证码: " + code + "],还未过期";
        }

        //生成验证码
        code = UUID.randomUUID().toString().substring(0, 6);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        boolean isSend = smsService.send(phoneNumber, "", map);

        //如果发送成功，就放入redis中
        if (isSend) {
            redisTemplate.opsForValue().set(phoneNumber, code, 1, TimeUnit.MINUTES);
            return "[手机号: " + phoneNumber + "],[验证码: " + code + "],发送成功";
        } else {
            return "[手机号:" + phoneNumber + "],[验证码:" + code + "],发送失败";
        }
    }
}
