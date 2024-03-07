package com.pxl.service.Impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.common.utils.UserInfoUtils;
import com.pxl.entity.AdminUser;
import com.pxl.entity.Article;
import com.pxl.entity.dto.ArticleDto;
import com.pxl.entity.dto.ArticleQueryDto;
import com.pxl.entity.vo.ArticleVo;
import com.pxl.mapper.ArticleMapper;
import com.pxl.mapper.UserMapper;
import com.pxl.service.ArticleService;
import com.pxl.service.UserCategoryRelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@CrossOrigin
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    private final ArticleMapper articleMapper;
    private final UserCategoryRelService userCategoryRelService;
    private final UserMapper userMapper;

    // 向量内积计算。
    public static Integer cosineSimilarity(Integer[] vectorA, Integer[] vectorB) {
        Integer dotProduct = 0;
        for (int i = 0; i < vectorA.length; i++) {
            // 内积相乘，已优化
            dotProduct += vectorB[vectorA[i]-1];
        }
        return dotProduct;
    }


    // 推荐算法
    public ArrayList<ArticleVo> recommend(ArrayList<ArticleVo> articleVos){
        // 先获得用户id
        LambdaQueryWrapper<AdminUser> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(AdminUser::getUsername,UserInfoUtils.getCurrentUsername());
        AdminUser user = userMapper.selectOne(wrapper1);
        // 先获得用户画像偏好向量
        Integer[] perferences = userCategoryRelService.selectPerferencesByUserId(user.getId());
        for (int i = 0; i < articleVos.size(); i++) {
            Long articleId = Long.valueOf(articleVos.get(i).getId());
            // 获取每一篇文章画像的偏好向量
            Integer[] categoryId = articleMapper.getCategoryByArticleId(articleId);
            // 计算内积保留相似性关系值。
            articleVos.get(i).setRecommendValue(cosineSimilarity(categoryId,perferences));
        }
        //按照相似性计算结果排序
        Collections.sort(articleVos);
        return articleVos;
    }

    @Override
    public List<ArticleVo> findAll(ArticleQueryDto articleQueryDto) {
        ArrayList<ArticleVo> articleVos = null;
        // 根据分类排序
        // 默认根据创建时间降序
        if(articleQueryDto.getQueryWrapper().equals(1)){
            String orderByStr = "order by create_time desc";
            // 是否查询所有 根据categoryId为0
            if(articleQueryDto.getArticleCategoryId().equals(0)){
                // 用户没有登录
                System.out.println(UserInfoUtils.getCurrentUsername().isEmpty());
                articleVos = articleMapper.selectAllByWrapper(orderByStr);
            }else{
                articleVos = articleMapper.selectByWrapper(articleQueryDto.getArticleCategoryId(),orderByStr);
            }

        }else if(articleQueryDto.getQueryWrapper().equals(2)){
            // 根据喜欢数量降序
            String orderByStr = "order by article_like_nums desc";
            if(articleQueryDto.getArticleCategoryId().equals(0)){
                articleVos = articleMapper.selectAllByWrapper(orderByStr);
            }else{
                articleVos = articleMapper.selectByWrapper(articleQueryDto.getArticleCategoryId(),orderByStr);
            }
            // 如果未登录，则直接根据热榜返回
            if(UserInfoUtils.getCurrentUsername().equals("anonymousUser")){
                //pass
            }else{
                // todo:推荐算法
                articleVos = recommend(articleVos);
            }
        }
        // 查找所有的文章的所有标签数组，前端需要展示
        ArticleVo articleVo = null;
        for (int i = 0; i < articleVos.size(); i++) {
            articleVo = articleVos.get(i);
            String[] categoryNameArr = articleMapper.selectCategoryNameArr(articleVo.getId());
            for (int i1 = 0; i1 < categoryNameArr.length; i1++) {
            }
            articleVo.setCategoryNameArr(categoryNameArr);
            articleVos.set(i,articleVo);
        }
        return articleVos;
    }

    @Override
    public void addLikeNums(Long articleId) {
        articleMapper.addLikeNums(articleId);
    }

    public List<ArticleVo> findArticleHot(){
        return articleMapper.findArticleHot();
    }

    @Override
    public List<ArticleVo> findAllByKeywords(String keywords) {
        return articleMapper.findAllByKeywords(keywords);
    }

    @Override
    public Integer addViewNums(Long id){return articleMapper.addViewNums(id);}

    @Override
    public void saveArticleCategoryRel(ArticleDto article) {
        String[] categoryIdArr = article.getCategoryIdArr();
        for (int i = 0; i < categoryIdArr.length; i++) {
            articleMapper.saveCategory(article.getId(), Integer.valueOf(categoryIdArr[i]));
        }
    }

    @Override
    public Integer[] getCategoryByArticleId(Long articleId) {
        return articleMapper.getCategoryByArticleId(articleId);
    }

}
