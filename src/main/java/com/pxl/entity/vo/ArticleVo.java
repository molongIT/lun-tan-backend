package com.pxl.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class ArticleVo implements Comparable{

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String userId;
    private String username;
    private String avatar;
    private String articleDescription;
    private String articleUrl;
    private String articleTitle;
    private Integer articleCategoryId;
    private Integer articleLikeNums;
    private Integer articleViewNums;
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    // 标签数组
    private String[] categoryNameArr;


    private Integer recommendValue;

    @Override
    public int compareTo(Object o) {
        ArticleVo o1 = (ArticleVo) o;
        return o1.getRecommendValue()-this.recommendValue;
    }
}
