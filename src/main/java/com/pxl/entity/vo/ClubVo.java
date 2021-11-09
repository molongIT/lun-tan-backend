package com.pxl.entity.vo;

import com.pxl.entity.Club;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ClubVo extends Club {

    private String clubPrincipal;

    private String clubPrincipalPhone;

    private List<ActivityForClubVo> clubActivities;

}
