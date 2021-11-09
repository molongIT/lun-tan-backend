package com.pxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxl.entity.Club;
import com.pxl.entity.ClubActivity;
import com.pxl.entity.vo.ActivityForClubVo;
import com.pxl.entity.vo.ActivityVo;

import java.util.List;

public interface ClubActivityService extends IService<ClubActivity> {
        List<ActivityVo> getAllActivity(String id);

}
