package com.pxl.common.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class LogConfig {
    private static final Logger logger = LoggerFactory.getLogger(LogConfig.class);

    @Bean
    public Person logMethod(){
        //可以调整日志级别，仅对当前日志级别及以上级别有效。
        logger.trace("这是trace日志……");
        logger.debug("这是debug日志");
        logger.info("这是info日志");
        logger.warn("这是warn日志");
        logger.error("这是error日志");
        return new Person();
    }
}
