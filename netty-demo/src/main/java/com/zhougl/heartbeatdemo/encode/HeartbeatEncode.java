package com.zhougl.heartbeatdemo.encode;

import com.zhougl.heartbeatdemo.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhougl
 * 2019/2/13
 **/
public class HeartbeatEncode extends MessageToByteEncoder<CustomProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CustomProtocol customProtocol, ByteBuf byteBuf) throws Exception {
        byteBuf.writeLong(customProtocol.getId());
        byteBuf.writeBytes(customProtocol.getContent().getBytes());
    }
}
