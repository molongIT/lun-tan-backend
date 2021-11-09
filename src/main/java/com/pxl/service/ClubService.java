package com.pxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxl.common.ResultWrapper;
import com.pxl.entity.Club;
import com.pxl.entity.NextTodo;
import com.pxl.entity.vo.ClubVo;
import com.pxl.entity.vo.NextTodoVo;

import java.util.List;

public interface ClubService extends IService<Club> {


    List<ClubVo> getAll();

}
