package com.pxl.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ArticleComment {

    private Integer articleCommentId;

    private Integer userId;

    private Integer articleId;

    private String commentText;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

}
