package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.DTO.Systemlog;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LogMapper extends BaseMapper<Systemlog> {

    /*注解sql示例：*/
    @Select("select ip from systemlog where chrome = #{browser}")
    public void selectIps(String browser);
}
