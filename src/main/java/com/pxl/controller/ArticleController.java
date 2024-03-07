package com.pxl.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.pxl.common.ResultWrapper;
import com.pxl.common.annotation.AnonymousAccess;
import com.pxl.common.utils.UserInfoUtils;
import com.pxl.entity.AdminUser;
import com.pxl.entity.Article;
import com.pxl.entity.UserCategoryRel;
import com.pxl.entity.dto.ArticleDto;
import com.pxl.entity.dto.ArticleQueryDto;
import com.pxl.entity.ArticleComment;
import com.pxl.entity.vo.ArticleCommentVo;
import com.pxl.entity.vo.ArticleVo;
import com.pxl.service.ArticleCommentService;
import com.pxl.service.ArticleService;
import com.pxl.service.UserCategoryRelService;
import com.pxl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleCommentService articleCommentService;
    private final UserCategoryRelService userCategoryRelService;
    private final UserService userService;

    public void chat(){
        // 实例化BaiduService
        BaiduService baiduService = new BaiduService(your apiKey, your secretKey);
        // 构建请求参数
        EmbeddingV1Param param = EmbeddingV1Param.builder()
                .input(Collections.singletonList("文本向量"))
                .user_id("1")
                .build();
        // 发起请求，获取请求响应
        EmbeddingV1Response embeddingV1Response = baiduService.embeddingV1(param, baiduService.getToken());
        return;
    }

    /**
     * 获取文章列表，匿名可访问。
     */
    @GetMapping
    //@PreAuthorize("hasAnyAuthority('root','admin')")
    @AnonymousAccess
    public List<ArticleVo> getAll(@RequestParam Integer articleCategoryId, @RequestParam Integer queryWrapper) {
        ArticleQueryDto articleQueryDto = new ArticleQueryDto(articleCategoryId, queryWrapper);
        ArrayList<ArticleVo> all = (ArrayList<ArticleVo>) articleService.findAll(articleQueryDto);
        int size = all.size();
        if(size > 10){
            for (int i = size-1; i >= 10; i--) {
                all.remove(i);
            }
        }
        return all;
    }



    @GetMapping("/search")
    //@PreAuthorize("hasAnyAuthority('root','admin')")
    @AnonymousAccess
    public List<ArticleVo> search(@RequestParam String keywords) {
        return articleService.findAllByKeywords(keywords);
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public ResultWrapper add(@RequestBody ArticleDto article) {
        Article articlesave = new Article();
        Long id = IdWorker.getId();
        article.setId(id);
        BeanUtil.copyProperties(article,articlesave);
        articleService.save(articlesave);
        // 保存文章-标签关系
        articleService.saveArticleCategoryRel(article);
        return ResultWrapper.success();
    }

    @AnonymousAccess
    @GetMapping("/comment")
    public ResultWrapper selectArticleComment(@RequestParam String articleId) {
        List<ArticleCommentVo> articleCommentVos=articleCommentService.readArticleComment(articleId);
        return ResultWrapper.success(articleCommentVos);
    }

    @AnonymousAccess
    @GetMapping("/hot")
    public ResultWrapper selectHotArticles() {
        List<ArticleVo> hotArticles =articleService.findArticleHot();
        return ResultWrapper.success(hotArticles);
    }

    /**
     * 埋点2
     * @param articleId
     * @return
     */
    @PutMapping("/like/{articleId}")
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public ResultWrapper addLike(@PathVariable Long articleId) {
        // 待优化；查询用户id
        LambdaQueryWrapper<AdminUser> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(AdminUser::getUsername,UserInfoUtils.getCurrentUsername());
        AdminUser user = userService.getOne(wrapper1);

        Integer[] categoryIds = articleService.getCategoryByArticleId(articleId);

        for (int i = 0; i < categoryIds.length; i++) {
            LambdaUpdateWrapper<UserCategoryRel> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(UserCategoryRel::getUserId, user.getId());
            wrapper.eq(UserCategoryRel::getCategoryId,categoryIds[i]);
            wrapper.setSql("perference = perference + 2");
            userCategoryRelService.update(wrapper);
        }

        articleService.addLikeNums(articleId);
        return ResultWrapper.success();
    }

    /**
     * 发表评论
     */
    @PostMapping("/comment")
    @PreAuthorize("hasAnyAuthority('root','admin')")
    public ResultWrapper comment(@RequestBody ArticleComment articleComment) {

        // 待优化；查询用户id
        LambdaQueryWrapper<AdminUser> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(AdminUser::getUsername,UserInfoUtils.getCurrentUsername());
        AdminUser user = userService.getOne(wrapper1);

        Integer[] categoryIds = articleService.getCategoryByArticleId(articleComment.getArticleId());

        for (int i = 0; i < categoryIds.length; i++) {
            LambdaUpdateWrapper<UserCategoryRel> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(UserCategoryRel::getUserId, user.getId());
            wrapper.eq(UserCategoryRel::getCategoryId,categoryIds[i]);
            wrapper.setSql("perference = perference + 3");
            userCategoryRelService.update(wrapper);
        }

        articleCommentService.save(articleComment);
        return ResultWrapper.success();
    }

    /**
     * 埋点1
     * @param articleId
     * @return
     */
    @PostMapping("/view/{articleId}")
    @AnonymousAccess
    public ResultWrapper addViewNums(@PathVariable Long articleId){
        // todo:用户没登录的！！
        articleService.addViewNums(articleId);
        // todo：根据name=》id
        LambdaQueryWrapper<AdminUser> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(AdminUser::getUsername,UserInfoUtils.getCurrentUsername());
        AdminUser user = userService.getOne(wrapper1);
        // 偏好收集
        Integer[] categoryIds = articleService.getCategoryByArticleId(articleId);
        for (int i = 0; i < categoryIds.length; i++) {
            LambdaUpdateWrapper<UserCategoryRel> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(UserCategoryRel::getUserId, user.getId());
            wrapper.eq(UserCategoryRel::getCategoryId,categoryIds[i]);
            wrapper.setSql("perference = perference + 1");
            userCategoryRelService.update(wrapper);
        }
        return ResultWrapper.success();
    }

}
