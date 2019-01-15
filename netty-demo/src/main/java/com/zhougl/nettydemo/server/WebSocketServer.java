package com.zhougl.nettydemo.server;

import com.zhougl.nettydemo.handler.WebSocketServerHandler;
import com.zhougl.nettydemo.service.MyHttpService;
import com.zhougl.nettydemo.service.MyWebSocketService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhougl
 * 2018/11/25
 **/
public class WebSocketServer implements MyHttpService,MyWebSocketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    // 用于握手用的变量
    private static final AttributeKey<WebSocketServerHandshaker> ATTR_HAND_SHAKER = AttributeKey.newInstance("ATTR_KEY_CHANNEL_ID");

    private static final int MAX_CONTENT_LENGTH = 65536;

    private static final String WEBSOCKET_UPGRADE = "websocket";
    private static final String WEBSOCKET_CONNECTION = "Upgrade";
    private static final String WEBSOCKET_URI_ROOT_PATTERN = "ws://%s:%d";

    /**
     * 用户字段
     */
    private String host;
    private int port;

    private Map<ChannelId,Channel> channelMap = new HashMap<>();
    private String WEBSOCKET_URI_ROOT;

    public WebSocketServer(String host, int port) {
        this.host = host;
        this.port = port;
        WEBSOCKET_URI_ROOT = String.format(WEBSOCKET_URI_ROOT_PATTERN, host, port);
    }

    public void start(){
        // 实例化nio监听事件池
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 实例化nio工作线程池
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 启动器
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                // 将chanel 添加到map中
                channelMap.put(channel.id(),channel);
                LOGGER.info("new channel {}",channel);
                channel.closeFuture().addListener((ChannelFutureListener)channelFuture -> {
                    LOGGER.info("channel close future  {}",channelFuture);
                    // 关闭后，从map中删除掉
                    channelMap.remove(channel.id());
                });
                ChannelPipeline pipeline = channel.pipeline();
                // 添加http编码
                pipeline.addLast(new HttpServerCodec());
                // 聚合器
                pipeline.addLast(new HttpObjectAggregator(MAX_CONTENT_LENGTH));
                // 支持大数据流
                pipeline.addLast(new ChunkedWriteHandler());
                // 设置 websocket 服务处理方式
                pipeline.addLast(new WebSocketServerHandler(WebSocketServer.this,WebSocketServer.this));
            }
        });
        // 实例化后，需要进行端口绑定
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(host, port).addListener((ChannelFutureListener) channelFuture1 -> {
                if(channelFuture1.isSuccess()){
                    LOGGER.info("webSocket start");
                }
            }).sync();
            channelFuture.channel().closeFuture().addListener((ChannelFutureListener) channelFuture1 -> {
                LOGGER.info("server channel {} is closed",channelFuture1.channel());
            }).sync();
        } catch (InterruptedException e) {
            LOGGER.info("端口绑定失败");
//            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        LOGGER.info("webSocket shutdown");
    }

    @Override
    public void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request) {
        if(isWebSocketUpgrade(request)){
            LOGGER.info("请求是webSocket协议");
            // 获取子协议
            String subProtocols = request.headers().get(HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);
            // 握手工厂 设置 uri+协议+不允许扩展
            WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory(WEBSOCKET_URI_ROOT,subProtocols,false);
            // 从工厂中实例化一个 握手请求
            WebSocketServerHandshaker handshaker = handshakerFactory.newHandshaker(request);
            if(handshaker == null){
                //握手失败：不支持的协议
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
            }else{
                //响应请求:将 握手转交给 channel处理
                handshaker.handshake(ctx.channel(),request);
                //将 channel 与 handshaker 绑定
                ctx.channel().attr(ATTR_HAND_SHAKER).set(handshaker);
            }
        }else{
            LOGGER.info("不处理http请求");
        }
    }

    @Override
    public void handleWebSocketRequest(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if(frame instanceof TextWebSocketFrame){
            String text = ((TextWebSocketFrame) frame).text();
            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(text);
            LOGGER.info("receive textWebSocketFrame from channel {},online size is {},text is {}",ctx.channel(),channelMap.size(),text);
            // 发送给其他channel
            for (Channel channel : channelMap.values()){
                if(channel.equals(ctx.channel())){
                    continue;
                }
                // 将消息写出
                channel.writeAndFlush(textWebSocketFrame);
                LOGGER.info("消息已经发送给：{}",channel);
                LOGGER.info("write text:{} to {}",textWebSocketFrame,ctx.channel());
            }
            return;
        }
        // ping 回复 pong
        if(frame instanceof PingWebSocketFrame){
            LOGGER.info("receive pingWebSocket from channel: {}",ctx.channel());
            ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.retain().content()));
        }
        // pong do nothing
        if(frame instanceof PongWebSocketFrame){
            LOGGER.info("receive pongWebSocket from channel: {}",ctx.channel());
        }
        // close frame,than close
        if(frame instanceof CloseWebSocketFrame){
            LOGGER.info("receive closeWebSocket from channel: {}",ctx.channel());
            // 获取握手信息
            WebSocketServerHandshaker handshaker = ctx.channel().attr(ATTR_HAND_SHAKER).get();
            if (handshaker == null){
                LOGGER.error("channel: {} has no handShaker", ctx.channel());
                return;
            }
            handshaker.close(ctx.channel(), ((CloseWebSocketFrame) frame).retain());
            return;
        }
        // 剩下的都是 二进制 frame ，忽略
        LOGGER.warn("receive binary frame , ignore to handle");
    }

    private boolean isWebSocketUpgrade(FullHttpRequest request){
        HttpHeaders headers = request.headers();
        return request.method().equals(HttpMethod.GET)
                && headers.get(HttpHeaderNames.UPGRADE).contains(WEBSOCKET_UPGRADE)
                && headers.get(HttpHeaderNames.CONNECTION).contains(WEBSOCKET_CONNECTION);
    }
}
