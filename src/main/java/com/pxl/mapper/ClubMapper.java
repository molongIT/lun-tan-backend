package com.pxl.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.Club;
import com.pxl.entity.ClubActivity;
import com.pxl.entity.vo.ActivityForClubVo;
import com.pxl.entity.vo.ClubVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubMapper {
    List<ClubVo> getAll();
}
