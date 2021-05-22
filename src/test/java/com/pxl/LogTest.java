package com.pxl;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogTest {
    private static final Logger logger = LoggerFactory.getLogger(LogTest.class);


    @Test
    public void contextLoad(){
        //可以调整日志级别，仅对当前日志级别及以上级别有效。
        logger.warn("learning");
    }
}
