package com.zhougl.nettydemo.handler;

import com.zhougl.nettydemo.service.MyHttpService;
import com.zhougl.nettydemo.service.MyWebSocketService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhougl
 * 2018/11/25
 **/
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServerHandler.class);

    private MyHttpService myHttpService;
    private MyWebSocketService myWebSocketService;

    // 创建ChannelGroup,来保存每个已经建立连接的channel
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public WebSocketServerHandler(MyHttpService myHttpService, MyWebSocketService myWebSocketService) {
        super();
        this.myHttpService = myHttpService;
        this.myWebSocketService = myWebSocketService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        if(msg instanceof FullHttpRequest){
            myHttpService.handleHttpRequest(channelHandlerContext,(FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame){
            myWebSocketService.handleWebSocketRequest(channelHandlerContext,(WebSocketFrame) msg);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
        channels.writeAndFlush(new TextWebSocketFrame(ctx.channel() + "上线了"));
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());
        channels.writeAndFlush(new TextWebSocketFrame(ctx.channel() + "下线了"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        channels.remove(ctx.channel());
        ctx.close();
        LOGGER.info("异常信息: {}",cause.getMessage());
    }
}
