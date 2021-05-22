package com.pxl.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public interface MqService {

    /**
     * 发送消息
     * @param data
     * @param time 有效时间
     * @return
     */
    String sendMsg(Map data,String time);


    //消费消息
    //从指定队列主动拉消息
    boolean consumeMsg(String queue) throws Exception;

}
