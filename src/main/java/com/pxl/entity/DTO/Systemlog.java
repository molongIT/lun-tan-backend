package com.pxl.entity.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pxl.entity.DTO.BaseDTO;
import com.sun.org.apache.xalan.internal.xsltc.trax.DOM2TO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
public class Systemlog extends BaseDTO {

    @TableId(type = IdType.ID_WORKER)
    //指定使用MP默认的雪花生成算法（生成19位的数字）
    private Long id;

    //日志类型
    private String logType;

    private String description;

    private Date optime;

    private String ip;

    private String browser;

    private String func;

    private String params;

    //异常内容
    private String exceptionDetail;
}