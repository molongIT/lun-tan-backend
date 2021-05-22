package com.pxl.service;

import com.pxl.entity.AdminUser;

public interface UserService {
    public AdminUser findByName(String name);
}
