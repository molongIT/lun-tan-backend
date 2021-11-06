package com.pxl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.Article;
import com.pxl.entity.dto.ArticleHotDto;
import com.pxl.entity.dto.ArticleQueryDto;
import com.pxl.entity.vo.ArticleVo;
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
    public List<ArticleVo> findAll(ArticleQueryDto articleQueryDto) {
        List<ArticleVo> articleVos = null;
        // 根据分类排序
        // 默认根据创建时间降序
        if(articleQueryDto.getQueryWrapper().equals(1)){
            String orderByStr = "order by create_time desc";
            articleVos = articleMapper.selectByWrapper(articleQueryDto.getArticleCategoryId(),orderByStr);
        }else if(articleQueryDto.getQueryWrapper().equals(2)){
            // 根据喜欢数量降序
            String orderByStr = "order by article_like_nums desc";
            articleVos = articleMapper.selectByWrapper(articleQueryDto.getArticleCategoryId(),orderByStr);
        }
        return articleVos;
    }

    @Override
    public void addLikeNums(String articleId) {
        articleMapper.addLikeNums(articleId);
    }

    public List<ArticleVo> findArticleHot(){
        return articleMapper.findArticleHot();
    }

    @Override
    public List<ArticleVo> findAllByKeywords(String keywords) {
        return articleMapper.findAllByKeywords(keywords);
    }

    @Override
    public Integer addViewNums(Integer id){return articleMapper.addViewNums(id);}

}
