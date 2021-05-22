package com.pxl.entity.PO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;

@Data
public class Article implements Serializable {
    @TableId(type = IdType.INPUT)
    private String id;
    private String articleImg;
    private String articleAuthor;
    private String articleWord;
    private String articleTitle;
    private String createTime;
}
