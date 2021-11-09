package com.pxl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class Club {

    private String clubId;

    private String clubName;

    private String clubLogo;

    private String userId;

    private String clubDescription;

    private Date createTime;

}
