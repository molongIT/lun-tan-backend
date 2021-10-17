package com.pxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxl.entity.ArticleComment;
import com.pxl.entity.vo.ArticleCommentVo;

import java.util.List;

public interface ArticleCommentService extends IService<ArticleComment> {
    List<ArticleCommentVo> readArticleComment(String articleId);
}
