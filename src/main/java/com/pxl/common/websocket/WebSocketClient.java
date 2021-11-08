package com.pxl.common.websocket;

import lombok.Data;

import javax.websocket.Session;

/**
 * @author pxlong
 * 每一个连接client对象
 */
@Data
public class WebSocketClient {

    private String uri;

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
}
