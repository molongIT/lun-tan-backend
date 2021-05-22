package com.pxl;

import com.pxl.entity.Systemlog;
import com.pxl.mapper.LogMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SqlTest {


    @Autowired
    LogMapper logMapper;


    @Test
    public void logInsertTest(){
        Systemlog systemlog = new Systemlog();
        systemlog.setId(2416628L);
        systemlog.setIp("127.0.0.1");
        systemlog.setOptime(new Date());
        systemlog.setParams("test");
        systemlog.setFunc("c22");
        logMapper.insert(systemlog);
    }
}
