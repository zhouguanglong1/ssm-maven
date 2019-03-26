package com.zhougl.heartbeatdemo.server;

import com.zhougl.heartbeatdemo.protocol.CustomProtocol;
import com.zhougl.heartbeatdemo.util.NettySocketHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhougl
 * 2019/2/15
 **/
public class HeartbeatSimpleHandler extends SimpleChannelInboundHandler<CustomProtocol> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatSimpleHandler.class);

    private static final ByteBuf HEART_BEAT = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(new CustomProtocol(123456L,"pong").toString(), CharsetUtil.UTF_8));

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CustomProtocol customProtocol) throws Exception {
        LOGGER.info("收到CustomProtocol = {}",customProtocol);
        //保存客户端与 Channel 之间的关系
        NettySocketHolder.put(customProtocol.getId(), (NioSocketChannel) channelHandlerContext.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettySocketHolder.remove((NioSocketChannel) ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if(idleStateEvent.state() == IdleState.WRITER_IDLE){
                LOGGER.info("已经5秒没有收到消息！");
                ctx.writeAndFlush(HEART_BEAT).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
