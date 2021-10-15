package com.pxl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.Article;
import com.pxl.entity.ArticleComment;
import com.pxl.mapper.ArticleCommentMapper;
import com.pxl.mapper.ArticleMapper;
import com.pxl.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Service
@RequiredArgsConstructor
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {
    private final ArticleCommentMapper articleCommentMapper;
    @Override
    public void readArticleComment(String articleId) {
        articleCommentMapper.readArticleComment(articleId);
    }
}
