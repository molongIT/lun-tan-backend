package com.pxl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.Article;
import com.pxl.entity.ArticleComment;
import com.pxl.mapper.ArticleCommentMapper;
import com.pxl.mapper.ArticleMapper;
import com.pxl.service.ArticleCommentService;
import org.springframework.stereotype.Service;

@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

}
