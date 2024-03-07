package com.pxl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pxl.entity.UserCategoryRel;

public interface UserCategoryRelService extends IService<UserCategoryRel>{

    Integer[] selectPerferencesByUserId(String id);

}
