package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.Club;
import com.pxl.entity.ClubActivity;
import com.pxl.entity.vo.ActivityForClubVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubActivityMapper extends BaseMapper<ClubActivity> {

    @Select("select t1.id as activityId,t1.activity_name,t1.create_time as activityCreateTime from club_activity t1 where t1.club_id = #{clubId}")
    List<ActivityForClubVo> getActivityForClubVoByClubId(String clubId);

}
