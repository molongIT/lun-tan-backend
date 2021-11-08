package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.NextTodo;
import com.pxl.entity.vo.NextTodoVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface NextTodoMapper extends BaseMapper<NextTodo> {

    @Select("select t1.*,t2.avatar,t2.username from next_todo t1,admin_user t2 where t1.user_id = t2.id\n" +
            "order by t1.create_time desc \n" +
            "limit 8")
    List<NextTodoVo> selectLimitNextTodoVos();

}
