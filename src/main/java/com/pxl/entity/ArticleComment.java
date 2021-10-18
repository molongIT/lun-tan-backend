package com.pxl.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ArticleComment {

    @TableId(type = IdType.ASSIGN_ID)
    private Long articleCommentId;

    private Long userId;

    private Long articleId;

    private String articleCommentText;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

}
