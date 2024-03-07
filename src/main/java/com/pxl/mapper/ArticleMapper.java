package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.Article;
import com.pxl.entity.vo.ArticleVo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    @Update("update article set article_like_nums = article_like_nums + 1 where id = #{articleId}")
    void addLikeNums(Long articleId);

    @Select("select t1.*,t2.username,t2.avatar from article t1,admin_user t2 " +
            "where t1.user_id = t2.id " +
            "order by t1.article_like_nums desc " +
            "limit 10")
    List<ArticleVo> findArticleHot();

    @Select("select t1.*,t2.username,t2.avatar from article t1,admin_user t2,article_category_rel t3\n" +
            "where t1.user_id = t2.id and t1.id = t3.article_id\n" +
            "and t3.category_id = #{articleCategoryId} ${orderByStr}")
    ArrayList<ArticleVo> selectByWrapper(Integer articleCategoryId, String orderByStr);

    @Select("select t1.*,t2.username,t2.avatar from article t1,admin_user t2 " +
            "where t1.user_id = t2.id  ${orderByStr}")
    ArrayList<ArticleVo> selectAllByWrapper(String orderByStr);

    @Select("select t1.*,t2.username,t2.avatar " +
            "from article t1,admin_user t2 " +
            "where t1.user_id = t2.id and t1.article_title like '%${keywords}%' ")
    List<ArticleVo> findAllByKeywords(String keywords);

    @Update("update article set article_view_nums = article_view_nums + 1 where id = #{id}")
    Integer addViewNums(Long id);

    @Select("select t2.category_name\n" +
            "from article t1,article_category t2,article_category_rel t3\n" +
            "where t1.id = ${id} and t1.id = t3.article_id and t3.category_id = t2.id\n" +
            "ORDER BY t2.id")
    String[] selectCategoryNameArr(String id);

    @Update("INSERT into article_category_rel(article_id,category_id)\n" +
            "VALUES(${article_id},${category_id})")
    void saveCategory(Long article_id, Integer category_id);

    @Select("select t1.category_id\n" +
            "from article_category_rel t1\n" +
            "where t1.article_id = ${category_id}")
    Integer[] getCategoryByArticleId(Long category_id);

}
