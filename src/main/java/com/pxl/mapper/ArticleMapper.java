package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.Article;
import com.pxl.entity.dto.ArticleHotDto;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    @Update("update article set article_like_nums = article_like_nums + 1 where id = #{articleId}")
    void addLikeNums(String articleId);
    @Select("select article_title,id,article_like_nums from article order by article_like_nums desc limit 15")
    List<ArticleHotDto> findArticleHot();

}
