package com.pxl.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
@Builder
public class Article implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String articleImg;
    private String articleAuthor;
    private String articleDescription;
    private String articleUrl;
    private String articleTitle;
    private Integer articleCategoryId;
    private Integer articleLikeNums;
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

}
