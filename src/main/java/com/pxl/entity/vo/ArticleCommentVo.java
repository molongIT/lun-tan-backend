package com.pxl.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class ArticleCommentVo {
    private Long articleCommentId;

    private String articleCommentText;

    private String username;

    private String userAvatar;

    private String createTime;

}
