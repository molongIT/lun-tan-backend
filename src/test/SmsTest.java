package test;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.Test;

import java.util.HashMap;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.5.3</version>
</dependency>
*/
public class SmsTest {

    @Test
    public void test() {
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
        request.putQueryParameter("PhoneNumbers", "18866777388");
        // 签名，这里的 Value 就是在阿里云上申请的 签名
        request.putQueryParameter("SignName", "小龙龙服务平台");
        // 模板，这里的 Value 就是在阿里云上申请的模板的 模版CODE 值
        request.putQueryParameter("TemplateCode", "SMS_210995483");
        // 验证码，真实应用需要自动构建验证码
        HashMap<String,Object> map = new HashMap<>();
        map.put("code", 112233);
        request.putQueryParameter("TemplateParam",JSONObject.toJSONString(map));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
