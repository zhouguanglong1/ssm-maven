package com.zhougl.heartbeatdemo.server;

import com.zhougl.heartbeatdemo.decode.HeartbeatDecoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author zhougl
 * 2019/2/15
 **/
public class HeartBeatInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                // 五秒没有收到消息 将IdleStateHandler 添加到 ChannelPipeline 中
                .addLast(new IdleStateHandler(0,5,0))
                .addLast(new HeartbeatDecoder())
                .addLast(new HeartbeatSimpleHandler());
    }
}
