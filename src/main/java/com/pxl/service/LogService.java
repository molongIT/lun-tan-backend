package com.pxl.service;

import com.pxl.entity.DTO.Systemlog;

import java.util.List;

public interface LogService {
   List<Systemlog> getAll();

   void insert(Systemlog systemlog);
}
