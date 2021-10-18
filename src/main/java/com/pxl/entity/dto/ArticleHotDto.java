package com.pxl.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ArticleHotDto {
    private String articleTitle;
    private Integer id;
    private Integer articleLikeNums;

}
