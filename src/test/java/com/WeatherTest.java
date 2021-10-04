package com;


import com.pxl.common.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WeatherTest {

    @Test
    public void test() {
        //1、先根据deviceId获取location
        //2、根据location获取天气实况信息

        String host = "https://apifreelat.market.alicloudapi.com";
        String path = "/whapi/json/aliweather/briefcondition";
        String method = "POST";
        String appcode = "f4f40f38558a48c6a1f52f71665a9b66";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("lat", "31.9217");//纬度
        bodys.put("lon", "118.879");//经度
        bodys.put("token", "a231972c3e7ba6b33d8ec71fd4774f5e");

        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
