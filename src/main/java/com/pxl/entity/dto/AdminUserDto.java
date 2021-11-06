package com.pxl.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("登录用户类")
public class AdminUserDto {

    private String username;

    private String password;

    @Override
    public String toString() {
        return "username=" + username  + ", password="+ password;
    }
}
