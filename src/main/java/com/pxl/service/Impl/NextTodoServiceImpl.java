package com.pxl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.NextTodo;
import com.pxl.entity.vo.NextTodoVo;
import com.pxl.mapper.NextTodoMapper;
import com.pxl.service.NextTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NextTodoServiceImpl  extends ServiceImpl<NextTodoMapper, NextTodo> implements NextTodoService {

    private final NextTodoMapper nextTodoMapper;

    @Override
    public List<NextTodoVo> getLimitNums() {
        return nextTodoMapper.selectLimitNextTodoVos();
    }

}
