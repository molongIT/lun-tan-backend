package com.pxl.common.security.Jwt;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.pxl.common.redis.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils implements InitializingBean {

    private final JwtProperties properties;
    private final RedisTemplate redisTemplate;
    private static Key key;
    private static final String AUTHORITIES_KEY = "auth";

    /**
     * 生成token
     * @param authentication 表示身份验证请求或身份验证主体的令牌
     * @return
     */
    public static String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                // 权限信息
                .claim(AUTHORITIES_KEY,authorities)
                //用于对JWT进行数字签名的签名密钥。alg与密钥一起使用的JWS算法，以对JWT进行数字签名，从而生成JWS。
                //采用什么算法是可以自己选择的，不一定非要采用HS512
                .signWith(key,SignatureAlgorithm.HS512)
                .setId(IdUtil.simpleUUID())
                //.setExpiration(generateExpirationDate())
                .compact();
    }

    /**
     * 创建用于对JWT进行数字签名的签名密钥，这里在属性bean注入后再执行。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 通过token获取出authentication对象
     */
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        //获取到authentication
        Object authoritiesStr = claims.get(AUTHORITIES_KEY);
        Collection<? extends GrantedAuthority> authorities =
                authoritiesStr != null ?
                        Arrays.stream(authoritiesStr.toString().split(","))
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList()) : Collections.emptyList();
        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * 从请求头中获取token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request){
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }

    /**
     * @param token 需要检查的token
     */
    public void checkRenewal(String token){
        String role = getAuthentication(token).getAuthorities().toArray()[0].toString();
        // 判断是否续期token,计算token的过期时间(还剩多少时间)
        long time = RedisUtils.getExpire(redisTemplate,role+properties.getOnlineKey() + token) * 1000;
        Date expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) time);
        // 判断当前时间与过期时间的时间差
        long differ = expireDate.getTime() - System.currentTimeMillis();
        // 如果在续期检查的范围内，则续期
        if(differ <= properties.getDetect()){
            long renew = time + properties.getRenew();
            RedisUtils.expire(redisTemplate,role + properties.getOnlineKey() + token, renew, TimeUnit.MILLISECONDS);
        }
    }
}


