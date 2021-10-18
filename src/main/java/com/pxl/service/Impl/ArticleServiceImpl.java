package com.pxl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.Article;
import com.pxl.entity.dto.ArticleHotDto;
import com.pxl.entity.dto.ArticleQueryDto;
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
    public List<Article> findAll(ArticleQueryDto articleQueryDto) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getArticleCategoryId,articleQueryDto.getArticleCategoryId());
        // 默认根据创建时间降序
        if(articleQueryDto.getQueryWrapper().equals(1)){
            wrapper.orderByDesc(Article::getCreateTime);
        }else if(articleQueryDto.getQueryWrapper().equals(2)){
            // 根据喜欢数量降序
            wrapper.orderByDesc(Article::getArticleLikeNums);
        }
        return articleMapper.selectList(wrapper);
    }

    @Override
    public void addLikeNums(String articleId) {
        articleMapper.addLikeNums(articleId);
    }

    public List<ArticleHotDto> findArticleHot(){
        return articleMapper.findArticleHot();
    }

}
