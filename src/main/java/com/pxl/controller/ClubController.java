package com.pxl.controller;

import com.pxl.common.ResultWrapper;
import com.pxl.entity.vo.ClubVo;
import com.pxl.service.ClubActivityService;
import com.pxl.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;
    private final ClubActivityService clubActivityService;

    @GetMapping
    public ResultWrapper getAll(){
       return ResultWrapper.success(clubService.getAll());
    }

    /**
     * 根据cludId，获取所有活动
     * @param clubId
     * @return ActivityVo
     */
    @GetMapping("/activities")
    public ResultWrapper getActivitiesByClubId(@RequestParam String clubId){
        return null;
    }
}
