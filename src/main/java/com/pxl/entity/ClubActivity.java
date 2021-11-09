package com.pxl.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ClubActivity {

    String clubId;

    String activityName;

    String actibityAddress;

    String userId;

    String activivtyContent;

    Date createTime;

    Date activityEndTime;

    Date activityStartTime;

}
