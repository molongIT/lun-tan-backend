package com.pxl.entity;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Club {

    private String id;

    private String clubName;

    private String clubLogo;

    private String userId;

    private String clubDescription;

    private Date createTime;

}
