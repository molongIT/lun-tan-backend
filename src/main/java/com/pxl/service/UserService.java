package com.pxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxl.entity.AdminUser;

public interface UserService extends IService<AdminUser> {
    AdminUser findByName(String name);
}
