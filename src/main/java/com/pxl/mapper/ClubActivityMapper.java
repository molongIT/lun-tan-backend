package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.Club;
import com.pxl.entity.ClubActivity;
import com.pxl.entity.vo.ActivityForClubVo;
import com.pxl.entity.vo.ActivityVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubActivityMapper extends BaseMapper<ClubActivity> {
    @Select("select club_activity.club_id,activity_name,activity_address,user_id,activity_content,create_time,activity_end_time,activity_start_time,username,phone,admin_user.id" +
            " from admin_user,club_activity where user_id=admin_user.id and club_id=#{id}")
    List<ActivityVo> selectAllActivity(String id);
}
