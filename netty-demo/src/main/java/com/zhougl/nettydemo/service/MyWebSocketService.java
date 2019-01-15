package com.zhougl.nettydemo.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @author zhougl
 * 2018/11/25
 **/
public interface MyWebSocketService {
    void handleWebSocketRequest(ChannelHandlerContext ctx, WebSocketFrame frame);
}
