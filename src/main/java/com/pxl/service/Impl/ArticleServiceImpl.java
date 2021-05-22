package com.pxl.service.Impl;

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
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public List<Article> findAll() {
        return articleMapper.selectList(null);
    }

}
