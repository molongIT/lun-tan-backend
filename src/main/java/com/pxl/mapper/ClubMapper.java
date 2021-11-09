package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.Club;
import com.pxl.entity.vo.ClubVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ClubMapper extends BaseMapper<Club> {

    @Select("select t1.*,t2.username as clubPrincipal,t2.phone as clubPrincipalPhone from club t1,admin_user t2\n" +
            "where t1.user_id = t2.id")
    List<ClubVo> getAll();
}
