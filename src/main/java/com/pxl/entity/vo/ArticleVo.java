package com.pxl.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class ArticleVo {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String userId;
    private String username;
    private String avatar;
    private String articleDescription;
    private String articleUrl;
    private String articleTitle;
    private Integer articleCategoryId;
    private Integer articleLikeNums;
    private Integer articleViewNums;
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
}
