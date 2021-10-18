package com.pxl.controller;

import com.pxl.common.ResultWrapper;
import com.pxl.common.annotation.AnonymousAccess;
import com.pxl.common.utils.UserInfoUtils;
import com.pxl.entity.Article;
import com.pxl.entity.dto.ArticleHotDto;
import com.pxl.entity.dto.ArticleQueryDto;
import com.pxl.entity.ArticleComment;
import com.pxl.entity.vo.ArticleCommentVo;
import com.pxl.service.ArticleCommentService;
import com.pxl.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleCommentService articleCommentService;
    /**
     * 获取文章列表，匿名可访问。
     */
    @GetMapping
    //@PreAuthorize("hasAnyAuthority('root','admin')")
    @AnonymousAccess
    public List<Article> getAll(@RequestParam Integer articleCategoryId, @RequestParam Integer queryWrapper) {
        ArticleQueryDto articleQueryDto = new ArticleQueryDto(articleCategoryId, queryWrapper);
        System.out.println(articleQueryDto);
        return articleService.findAll(articleQueryDto);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public ResultWrapper add(@RequestBody Article article) {
        article.setArticleAuthor(UserInfoUtils.getCurrentUsername());
        articleService.save(article);
        return ResultWrapper.success();
    }

    @AnonymousAccess
    @GetMapping("/comment")
    public ResultWrapper selectArticleComment(@RequestParam String articleId) {
        List<ArticleCommentVo> articleCommentVos=articleCommentService.readArticleComment(articleId);
        return ResultWrapper.success(articleCommentVos);
    }

    @AnonymousAccess
    @GetMapping("/hotarticle")
    public ResultWrapper selectArticleComment() {
        List<ArticleHotDto> articleHotDtos=articleService.findArticleHot();
        return ResultWrapper.success(articleHotDtos);
    }

    @PutMapping("/like/{articleId}")
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public ResultWrapper addLike(@PathVariable String articleId) {
        articleService.addLikeNums(articleId);
        return ResultWrapper.success();
    }

    /**
     * 发表评论
     */
    @PostMapping("/comment")
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public ResultWrapper comment(@RequestBody ArticleComment articleComment) {
        System.out.println(articleComment);
        articleCommentService.save(articleComment);
        return ResultWrapper.success();
    }

}
