package com.pxl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.Article;
import com.pxl.mapper.ArticleMapper;
import com.pxl.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;


@CrossOrigin
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public List<Article> findAll() {
        return articleMapper.selectList(null);
    }

}
