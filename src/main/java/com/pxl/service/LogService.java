package com.pxl.service;

import com.pxl.entity.Systemlog;
import com.pxl.mapper.LogMapper;

import java.security.PrivateKey;
import java.util.List;

public interface LogService {
   List<Systemlog> getAll();

   void insert(Systemlog systemlog);
}
