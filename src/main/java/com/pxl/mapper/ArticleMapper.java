package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.Article;
import com.pxl.entity.dto.ArticleHotDto;
import com.pxl.entity.vo.ArticleVo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    @Update("update article set article_like_nums = article_like_nums + 1 where id = #{articleId}")
    void addLikeNums(String articleId);

    @Select("select t1.*,t2.username,t2.avatar from article t1,admin_user t2 " +
            "where t1.user_id = t2.id " +
            "order by t1.article_like_nums desc " +
            "limit 10")
    List<ArticleVo> findArticleHot();

    @Select("select t1.*,t2.username,t2.avatar from article t1,admin_user t2 " +
            "where t1.user_id = t2.id and article_category_id = #{articleCategoryId} ${orderByStr}")
    List<ArticleVo> selectByWrapper(Integer articleCategoryId, String orderByStr);

    @Select("select t1.*,t2.username,t2.avatar " +
            "from article t1,admin_user t2 " +
            "where t1.user_id = t2.id and t1.article_title like '%${keywords}%' ")
    List<ArticleVo> findAllByKeywords(String keywords);
    @Update("update article set article_view_nums =article_view_nums+1 where id=#{id}")
    Integer addViewNums(Integer id);

}
