package com.pxl.controller;

import com.pxl.common.ResultWrapper;
import com.pxl.common.annotation.AnonymousAccess;
import com.pxl.entity.ClubActivity;
import com.pxl.entity.vo.ActivityForClubVo;
import com.pxl.entity.vo.ActivityVo;
import com.pxl.entity.vo.ClubVo;
import com.pxl.service.ClubActivityService;
import com.pxl.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;
    private final ClubActivityService clubActivityService;

    @GetMapping
    @AnonymousAccess
    public ResultWrapper getAll(){
       return ResultWrapper.success(clubService.getAll());
    }
    /**
     * 根据cludId，获取所有活动
     * @param clubId
     * @return ActivityVo
     */
    @GetMapping("/activities")
    @AnonymousAccess
    public ResultWrapper getActivitiesByClubId(@RequestParam String clubId){
        List<ActivityVo> activityVos=clubActivityService.getAllActivity(clubId);
        return ResultWrapper.success(activityVos);
    }
}
