package com.pxl.controller;

import com.pxl.entity.Article;
import com.pxl.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public List<Article> getAll(){
        return articleService.findAll();
    }

}
