package com.pxl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser implements Serializable {

    @TableId(type = IdType.INPUT)
    private Long id;

    private String username;

    private String nickname;

    private String phone;

    private String gender;

    private String avatarPath;

    @JsonIgnore
    private String password;

    private Boolean enabled;

    @JsonIgnore
    private Boolean isRoot;

    @Override
    public String toString() {
        return "AdminUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", isRoot=" + isRoot +
                '}';
    }
}
