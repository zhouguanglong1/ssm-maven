package com.zhougl.heartbeatdemo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhougl
 * 2019/2/13
 **/
@Component
public class HeartBeatClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeatClient.class);

    private EventLoopGroup group = new NioEventLoopGroup();

    @Value("${netty.server.port}")
    private int nettyPort;
    @Value("${netty.server.host}")
    private String host;
    private SocketChannel channel;

    @PostConstruct
    public void start() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new CustomerHandlerInitializer());

        ChannelFuture channelFuture = bootstrap.connect(host, nettyPort).sync();
        if(channelFuture.isSuccess()){
            LOGGER.info("netty启动成功");
        }
        channel = (SocketChannel) channelFuture.channel();
    }
}
