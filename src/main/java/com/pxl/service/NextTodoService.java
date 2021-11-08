package com.pxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxl.entity.NextTodo;
import com.pxl.entity.vo.NextTodoVo;
import java.util.List;

public interface NextTodoService extends IService<NextTodo> {

    List<NextTodoVo> getLimitNums();

}
