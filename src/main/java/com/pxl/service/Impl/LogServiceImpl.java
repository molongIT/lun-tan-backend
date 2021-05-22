package com.pxl.service.Impl;

import com.pxl.entity.DTO.Systemlog;
import com.pxl.mapper.LogMapper;
import com.pxl.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;

    @Override
    public List<Systemlog> getAll() {
        return logMapper.selectList(null);
    }

    @Override
    public void insert(Systemlog systemlog) {
        logMapper.insert(systemlog);
    }
}
