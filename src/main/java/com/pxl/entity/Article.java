package com.pxl.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Article implements Serializable {

    @TableId(type = IdType.INPUT)
    private Long id;
    private String userId;
    private String articleDescription;
    private String articleUrl;
    private String articleTitle;
    private Integer articleCategoryId;
    private Integer articleLikeNums;
    private Integer articleViewNums;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;

}
