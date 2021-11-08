package com.pxl.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NextTodo {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String userId;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

}
