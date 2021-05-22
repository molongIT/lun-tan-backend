package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;

/**
 * @author 逄晓龙
 * @date 2021年03月28日 10:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SecurityTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource(name = "RedisTemplate")
    private RedisTemplate redisTemplate;


    @Test
    public void test() {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9" +
                ".eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJyb290IiwianRpIjoiNGU1MWIzYzc0ZDQxNDQyOWJjMDhkZDY2N2Q4OTk2ZmQifQ" +
                ".QtoNS0x4Zy78mbDw3xDNcjoZt0OU4GlW_kr4hqkimosTvbKhQQH73ZeeeF3c_fLJkwKkJyiBR0F1ESizn5MNJA";
        if(token.startsWith("Bearer ")){
            System.out.println("true");
        }
    }
}
