package com.pxl.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.pxl.common.ResultWrapper;
import com.pxl.common.annotation.AnonymousAccess;
import com.pxl.common.utils.UserInfoUtils;
import com.pxl.common.websocket.WebSocketService;
import com.pxl.entity.AdminUser;
import com.pxl.entity.NextTodo;
import com.pxl.entity.dto.NextTodoDto;
import com.pxl.entity.vo.NextTodoVo;
import com.pxl.service.NextTodoService;
import com.pxl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nextTodo")
public class NextTodoController {

    private final NextTodoService nextTodoService;
    private final UserService userService;

    @GetMapping
    @AnonymousAccess
    public ResultWrapper getLimitNums(){
        List<NextTodoVo> limitNums = nextTodoService.getLimitNums();
        Collections.reverse(limitNums);
        return ResultWrapper.success(limitNums);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public ResultWrapper add(@RequestBody NextTodoDto nextTodoDto){
        System.out.println(nextTodoDto);
        NextTodo nextTodo = BeanUtil.toBean(nextTodoDto, NextTodo.class);
        // 保存到数据库
        nextTodoService.save(nextTodo);
        // 推送给在线用户
        // 封装为Vo类（nextToto+user）
        NextTodoVo nextTodoVo = BeanUtil.toBean(nextTodo, NextTodoVo.class);
        AdminUser user = userService.getById(nextTodo.getUserId());
        nextTodoVo.setAvatar(user.getAvatar());
        nextTodoVo.setUsername(user.getUsername());
        WebSocketService.sendGroupByPrefix("", JSONUtil.parse(nextTodoVo).toString());
        return ResultWrapper.success();
    }

    // 调试使用
    @AnonymousAccess
    @PostMapping("/test/{text}")
    public ResultWrapper addTest(@PathVariable String text){
        WebSocketService.sendGroupByPrefix("", text);
        return ResultWrapper.success();
    }

}
