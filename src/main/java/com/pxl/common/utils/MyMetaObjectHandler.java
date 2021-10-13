package com.pxl.common.utils;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component //表示交给Spring管理，否则自动填充不好使。
public class MyMetaObjectHandler implements MetaObjectHandler {



    //使用mp实现添加操作，这个方法执行
    @Override
    public void insertFill(MetaObject metaObject) {
        //属性名称，不是字段名称
        this.setFieldValByName("createTime", DateUtil.date().toLocaleString(), metaObject);
    }

    //使用mp实现修改操作，这个方法会执行。
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", DateUtil.date().toLocaleString(), metaObject);
    }
}
