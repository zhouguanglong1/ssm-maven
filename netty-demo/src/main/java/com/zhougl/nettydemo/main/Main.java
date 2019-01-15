package com.zhougl.nettydemo.main;

import com.zhougl.nettydemo.server.WebSocketServer;

/**
 * @author zhougl
 * 2018/11/25
 **/
public class Main {
    public static void main(String[] args){
        new WebSocketServer("localhost",9999).start();
    }
}
