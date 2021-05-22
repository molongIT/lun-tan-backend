package com.pxl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pxl.entity.PO.AdminUser;
import com.pxl.mapper.UserMapper;
import com.pxl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public AdminUser findByName(String name) {
        QueryWrapper<AdminUser> qw = new QueryWrapper<>();
        qw.eq("username", name);
        return (AdminUser) userMapper.selectList(qw).get(0);
    }

}
