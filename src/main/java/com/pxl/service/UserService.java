package com.pxl.service;

import com.pxl.entity.PO.AdminUser;

public interface UserService {
    public AdminUser findByName(String name);
}
