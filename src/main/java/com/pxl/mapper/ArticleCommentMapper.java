package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.ArticleComment;
import com.pxl.entity.vo.ArticleCommentVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//评论内容、用户名、头像路径
@Repository
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {
    @Select("select article_comment_id,article_comment_text,username,avatar as userAvatar,create_time " +
            "from article_comment,admin_user " +
            "where article_comment.user_id=admin_user.id and article_id=#{articleId} " +
            "order by create_time desc")
    List<ArticleCommentVo> readArticleComment(@Param("articleId") String articleId);


    @Select("select t1.perference\n" +
            "from user_category_rel t1\n" +
            "where t1.user_id = #{id} ORDER BY t1.category_id")
    Integer[] selectPerferencesByUserId(String id);

}
