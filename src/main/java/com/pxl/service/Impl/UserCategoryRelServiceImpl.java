package com.pxl.service.Impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pxl.entity.UserCategoryRel;
import com.pxl.mapper.ArticleCommentMapper;
import com.pxl.mapper.UserCategoryRelMapper;
import com.pxl.service.UserCategoryRelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCategoryRelServiceImpl extends ServiceImpl<UserCategoryRelMapper, UserCategoryRel> implements UserCategoryRelService {

    private final ArticleCommentMapper UserCategoryRelMapper;

    @Override
    public Integer[] selectPerferencesByUserId(String id) {
        return UserCategoryRelMapper.selectPerferencesByUserId(id);
    }
}
