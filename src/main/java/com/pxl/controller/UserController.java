package com.pxl.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pxl.common.ResultWrapper;
import com.pxl.common.annotation.AnonymousAccess;
import com.pxl.entity.AdminUser;
import com.pxl.entity.dto.AdminUserDto;
import com.pxl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping
    public ResultWrapper update(@RequestBody AdminUser user){
        userService.updateById(user);
        return ResultWrapper.success();
    }

    @PostMapping()
    @AnonymousAccess
    public ResultWrapper register(@RequestBody AdminUserDto user){
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(user.getUsername());
        adminUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        adminUser.setAvatar("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202004%2F14%2F20200414210224_dnzpo.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1638682490&t=1f5b0e2a58873efb478d3ac9ec1b380c");
        userService.save(adminUser);
        return ResultWrapper.success();
    }

    @GetMapping()
    @AnonymousAccess
    public ResultWrapper get(@RequestParam String username){
        LambdaQueryWrapper<AdminUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminUser::getUsername,username);
        return ResultWrapper.success(userService.getOne(wrapper));
    }
}
