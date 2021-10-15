package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.Article;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    @Update("update article set article_like_nums = article_like_nums + 1 where id = #{articleId}")
    void addLikeNums(String articleId);

}
