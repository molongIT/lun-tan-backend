package com.pxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxl.entity.Article;
import com.pxl.entity.dto.ArticleHotDto;
import com.pxl.entity.dto.ArticleQueryDto;
import com.pxl.entity.vo.ArticleVo;

import java.util.List;

public interface ArticleService extends IService<Article> {

    List<ArticleVo> findAll(ArticleQueryDto articleQueryDto);

    void addLikeNums(String articleId);

    List<ArticleHotDto> findArticleHot();

    List<ArticleVo> findAllByKeywords(String keywords);

}
