package com.pxl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;

@Data
public class Article implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String articleImg;
    private String articleAuthor;
    private String articleDescription;
    private String articleUrl;
    private String articleTitle;
    private String createTime;

}
