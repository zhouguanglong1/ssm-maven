package com.zhougl.javadesignpatterndemo.process_engine.command;

/**
 * @author by zhougl
 * @classname CommandContextInterceptor
 * @desc TODO
 * @date 2019/6/9 16:09
 */
public class CommandContextInterceptor extends AbstractCommandInterceptor {
    @Override
    public <T> T execute(Command<T> command) {
        return next.execute(command);
    }
}
