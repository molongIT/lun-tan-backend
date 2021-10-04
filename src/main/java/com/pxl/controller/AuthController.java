package com.pxl.controller;
import com.pxl.common.ResultWrapper;
import com.pxl.common.security.Jwt.JwtTokenUtils;
import com.pxl.common.security.OnlineUserService;
import com.pxl.entity.DTO.AdminUserDto;
import com.pxl.entity.DTO.JwtAdminUserDto;
import com.pxl.entity.DTO.OnlineAdminUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenUtils jwtTokenUtils;
    private final OnlineUserService onlineUserService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping
    public Map<String, Object> login(@RequestBody AdminUserDto user, HttpServletRequest request){
        // 生成一个UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        //去验证
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 生成令牌
        String token = JwtTokenUtils.createToken(authentication);
        log.info("token生成：{}", token);
        final JwtAdminUserDto user1 = (JwtAdminUserDto) authentication.getPrincipal();
        // 踢出之前登录
        onlineUserService.checkLoginOnUser(user1.getRoles().toArray()[0].toString(), OnlineAdminUserDto.class,
                user1.getUsername());
        // 保存在线信息
        onlineUserService.save(user1, token, request);
        //返回token与用户信息*/
        HashMap<String, Object> authInfo = new HashMap<String, Object>(2) {
            {
                put("token", token);
                put("userInfo", user1);
            }
        };
        System.out.println("----------------" + authInfo);
        return authInfo;
    }

    @DeleteMapping("/logout")
    public ResultWrapper logout(HttpServletRequest request){
        // 剔除token
        String token = request.getHeader("Authorization").replace("Bearer ","");
        onlineUserService.logout(token);
        return ResultWrapper.success(null);
    }
}
