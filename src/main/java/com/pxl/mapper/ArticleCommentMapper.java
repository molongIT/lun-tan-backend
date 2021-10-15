package com.pxl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pxl.entity.ArticleComment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {
    @Select("select article_comment_text,username,avatar_ path from article_comment,admin_user " +
            "where article_comment.user_id=admin_user.id and article_id=#{articleId}")
    void readArticleComment(String articleId);
}
