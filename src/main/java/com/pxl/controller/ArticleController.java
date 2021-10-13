package com.pxl.controller;

import com.pxl.common.ResultWrapper;
import com.pxl.common.annotation.AnonymousAccess;
import com.pxl.common.utils.UserInfoUtils;
import com.pxl.entity.Article;
import com.pxl.entity.dto.ArticleQueryDto;
import com.pxl.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 获取文章列表，匿名可访问。
     */
    @GetMapping
     @PreAuthorize("hasAnyAuthority('root','admin')")
//    @AnonymousAccess
    public List<Article> getAll(@RequestParam Integer articleCategoryId,@RequestParam Integer queryWrapper) {
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
}
