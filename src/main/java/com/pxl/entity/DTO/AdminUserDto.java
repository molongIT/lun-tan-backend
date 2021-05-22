package com.pxl.entity.DTO;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("登录用户类")
public class AdminUserDto {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Override
    public String toString() {
        return "username=" + username  + ", password="+ password;
    }
}
