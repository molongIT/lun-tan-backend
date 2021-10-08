package com.pxl.controller;

import com.pxl.common.ResultWrapper;
import com.pxl.common.annotation.AnonymousAccess;
import com.pxl.entity.Article;
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

    /**
     * 获取文章列表，匿名可访问。
     */
    @GetMapping
    // @PreAuthorize("hasAnyAuthority('root','admin')")
    @AnonymousAccess
    public List<Article> getAll() {
        return articleService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public ResultWrapper add(@RequestBody Article article) {
        articleService.save(article);
        return ResultWrapper.success();
    }


}
