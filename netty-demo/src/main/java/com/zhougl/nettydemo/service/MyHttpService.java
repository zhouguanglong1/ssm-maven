package com.zhougl.nettydemo.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author zhougl
 * 2018/11/25
 **/
public interface MyHttpService {
    void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request);
}
