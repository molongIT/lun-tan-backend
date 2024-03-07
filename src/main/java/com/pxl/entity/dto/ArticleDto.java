package com.pxl.entity.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class ArticleDto {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String userId;
    private String username;
    private String avatar;
    private String articleDescription;
    private String articleUrl;
    private String articleTitle;
    private Integer articleLikeNums;
    private Integer articleViewNums;
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    // 标签数组
    private String[] categoryIdArr;
}
