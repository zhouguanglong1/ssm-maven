package com.zhougl.heartbeatdemo.client;

import com.zhougl.heartbeatdemo.encode.HeartbeatEncode;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author zhougl
 * 2019/2/15
 **/
public class CustomerHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                //10 秒没发送消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(0,10,0))
                .addLast(new HeartbeatEncode())
                .addLast(new EchoClientHandler());
    }
}
