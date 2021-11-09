package com.pxl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.Club;
import com.pxl.entity.ClubActivity;
import com.pxl.mapper.ClubActivityMapper;
import com.pxl.mapper.ClubMapper;
import com.pxl.service.ClubActivityService;
import com.pxl.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubActivityServiceImpl extends ServiceImpl<ClubActivityMapper, ClubActivity> implements ClubActivityService {

    private final ClubActivityMapper clubActivityMapper;

}
