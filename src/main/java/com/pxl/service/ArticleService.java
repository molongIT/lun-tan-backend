package com.pxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxl.entity.Article;
import com.pxl.entity.dto.ArticleQueryDto;

import java.util.List;

public interface ArticleService extends IService<Article> {

    List<Article> findAll(ArticleQueryDto articleQueryDto);

    void addLikeNums(String articleId);

}
