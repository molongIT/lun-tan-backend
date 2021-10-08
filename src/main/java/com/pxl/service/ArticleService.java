package com.pxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxl.entity.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {

    List<Article> findAll();
}
