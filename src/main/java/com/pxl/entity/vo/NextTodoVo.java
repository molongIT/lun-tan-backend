package com.pxl.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NextTodoVo {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String userId;

    private String avatar;

    private String username;

    private String content;

    private String createTime;

}
