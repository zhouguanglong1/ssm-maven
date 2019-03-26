package com.zhougl.heartbeatdemo.util;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhougl
 * 2019/2/15
 **/
public class NettySocketHolder {
    private static final Map<Long,NioSocketChannel> MAP = new ConcurrentHashMap<>(16);

    public static void put(Long id,NioSocketChannel socketChannel){
        MAP.put(id,socketChannel);
    }

    public static NioSocketChannel get(Long id){
        return MAP.get(id);
    }

    public static void remove(NioSocketChannel socketChannel){
        MAP.entrySet().stream().filter(entry -> entry.getValue() == socketChannel).forEach(entry -> MAP.remove(entry.getKey()));
    }

    public static Map<Long, NioSocketChannel> getMAP() {
        return MAP;
    }
}
