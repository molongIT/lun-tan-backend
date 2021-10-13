package com.pxl.common.security;

import cn.hutool.json.JSONUtil;
import com.pxl.common.redis.RedisUtils;
import com.pxl.common.security.Jwt.JwtProperties;
import com.pxl.common.security.Jwt.JwtTokenUtils;
import com.pxl.entity.dto.JwtAdminUserDto;
import com.pxl.entity.dto.OnlineAdminUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineUserService {

    private final JwtTokenUtils jwtTokenUtils;
    private final JwtProperties properties;
    @Resource(name = "RedisTemplate")
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存在线用户信息
     * @param jwtAdminUserDto /
     * @param token /
     * @param request /
     */
    public void save(JwtAdminUserDto jwtAdminUserDto, String token, HttpServletRequest request){
        Set<String> roles = jwtAdminUserDto.getRoles();
        OnlineAdminUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineAdminUserDto(jwtAdminUserDto.getUsername(), roles, "chrome" , "127.0.0.1", "localhost",
                    new Date());
            log.info("在线用户信息：{}", onlineUserDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert onlineUserDto != null;
        RedisUtils.set(redisTemplate, roles.toArray()[0].toString() + properties.getOnlineKey() + token, JSONUtil.parseObj(onlineUserDto, false).setDateFormat("yyyy-MM-dd HH:mm:ss").toString(), properties.getTokenValidityInSeconds()/1000);
    }

    /**
     * 获取所有指定role的在线用户
     * @param role
     * @param T
     * @param <T>
     * @return
     */
    public <T> Map<String, T> getAll(String role, Class<T> T){
        List<String> keys = RedisUtils.scan(redisTemplate, role + properties.getOnlineKey() + "*");
        Collections.reverse(keys);
        Map<String, T> users = new HashMap<>();
        for (String key : keys){
            T user = (T) JSONUtil.toBean(JSONUtil.parseObj(RedisUtils.get(redisTemplate, key)), T.getComponentType());
            users.put(key, user);
        }
        return users;
    }

    /**
     * 退出登录
     * @param token /
     */
    public void logout(String token) {
        Authentication authentication = jwtTokenUtils.getAuthentication(token);
        String role = authentication.getAuthorities().toArray()[0].toString();
        String key = role + properties.getOnlineKey() + token;
        RedisUtils.del(redisTemplate, key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     * @param userName 用户名
     */
    public <T> void checkLoginOnUser(String role, Class<T> T, String userName){
        Map<String, T> users = getAll(role, T);
        if( users == null || users.isEmpty()){
            return;
        }
        for(String key : users.keySet()){
            if(users.get(key).toString().contains(userName)){
                RedisUtils.del(redisTemplate, key);
            }
        }
    }

}
