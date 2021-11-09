package com.pxl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.ClubActivity;
import com.pxl.entity.vo.ActivityForClubVo;
import com.pxl.entity.vo.ActivityVo;
import com.pxl.entity.vo.ClubVo;
import com.pxl.mapper.ClubActivityMapper;
import com.pxl.service.ClubActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubActivityServiceImpl extends ServiceImpl<ClubActivityMapper, ClubActivity> implements ClubActivityService {

    private final ClubActivityMapper clubActivityMapper;
    public List<ActivityVo> getAllActivity(String id){
        List<ActivityVo> clubActivities=clubActivityMapper.selectAllActivity(id);
        return clubActivities;
    }
}
