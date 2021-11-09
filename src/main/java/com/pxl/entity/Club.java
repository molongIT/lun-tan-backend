package com.pxl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class Club {

    String clubName;

    String clubId;

    String clubLogo;

    String userId;

    String clubDescription;

    Date createTime;
}
