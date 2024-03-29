package com.pxl.entity.vo;

import lombok.Data;

import java.util.Date;
@Data
public class ActivityVo {

    private String activityId;

    private String clubId;

    private String activityName;

    private String activityAddress;

    private String userId;

    private String activityContent;

    private Date activityCreateTime;

    private Date activityEndTime;

    private Date activityStartTime;

    private String id;

    private String username;

    private String userphone;
}
