package com.pxl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.ArticleComment;
import com.pxl.entity.vo.ArticleCommentVo;
import com.pxl.mapper.ArticleCommentMapper;
import com.pxl.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Service
@RequiredArgsConstructor
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {
    private final ArticleCommentMapper articleCommentMapper;
    @Override
    public List<ArticleCommentVo> readArticleComment(String articleId) {
        return articleCommentMapper.readArticleComment(articleId);
    }
}
