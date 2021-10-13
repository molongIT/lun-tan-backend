package com.pxl.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticleQueryDto {

    /**
     * 默认为1
     */
    private Integer articleCategoryId = 1;

    /**
     *  1：最新
     *  2：最热
     *  3：收藏
     */
    private Integer queryWrapper = 1;


}
