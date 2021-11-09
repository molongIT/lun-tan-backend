package com.pxl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.common.ResultWrapper;
import com.pxl.entity.Club;
import com.pxl.entity.NextTodo;
import com.pxl.entity.vo.ClubVo;
import com.pxl.entity.vo.NextTodoVo;
import com.pxl.mapper.ClubActivityMapper;
import com.pxl.mapper.ClubMapper;
import com.pxl.mapper.NextTodoMapper;
import com.pxl.service.ClubService;
import com.pxl.service.NextTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl extends ServiceImpl<ClubMapper, Club> implements ClubService {

    private final ClubMapper clubMapper;
    private final ClubActivityMapper clubActivityMapper;


    @Override
    public List<ClubVo> getAll() {
        List<ClubVo> clubVos = clubMapper.getAll();
        for(ClubVo clubVo : clubVos){
            clubVo.setClubActivities(clubActivityMapper.getActivityForClubVoByClubId(clubVo.getId()));
        }
        System.out.println(clubVos);
        return clubVos;
    }
}
