package com.zhougl.multithread.ratelimiter;


import java.util.concurrent.Callable;

/**
 * @author zhougl
 * 2019/4/19
 **/
public class Client implements Callable{
    private Server server;

    public Client(Server server) {
        this.server = server;
    }

    @Override
    public Object call() throws Exception {
        return server.remote();
    }
}
