package com.pxl.common.security.Jwt;

import com.pxl.common.redis.RedisUtils;
import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtTokenUtils jwtTokenUtils;

    private final JwtProperties properties;

    @Resource(name = "RedisTemplate")
    private final RedisTemplate redisTemplate;

    /**
     * 请求鉴权器：
     * 判断是否token，有token的话解析出Authentication认证实体
     * 然后去做一个判断Authentication是否有效：正确性、是否过期。
     * 如果没有token的话，就返回错误。
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException, java.io.IOException {
        String token = resolveToken(httpServletRequest);
        //token合法：
        if (StringUtils.hasText(token)) {
            Authentication authentication = jwtTokenUtils.getAuthentication(token);
             if(RedisUtils.hasKey(redisTemplate,
                     authentication.getAuthorities().toArray()[0].toString()+properties.getOnlineKey()+token)){
                log.info("用户姓名：{}，用户角色：{}，用户token：{}", authentication.getName(), authentication.getAuthorities(), token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                log.warn("用户已下线！！ 用户姓名：{}，用户角色：{}，用户token：{}", authentication.getName(), authentication.getAuthorities(), token);
            }
        // Token 续期
        jwtTokenUtils.checkRenewal(token);
    }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
}

    /**
     * 解析token
     *
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(properties.getHeader());
        bearerToken = bearerToken.substring(1, bearerToken.length() - 1);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            // 去掉令牌前缀
            return bearerToken.replace(properties.getTokenStartWith(), "");
        }
        return null;
    }


}
