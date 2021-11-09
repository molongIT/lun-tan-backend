package com.pxl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ClubActivity {

    private String id;

    private String clubId;

    private String activityName;

    private String activityAddress;

    private String userId;

    private String activityContent;

    private Date createTime;

    private Date activityEndTime;

    private Date activityStartTime;

}
