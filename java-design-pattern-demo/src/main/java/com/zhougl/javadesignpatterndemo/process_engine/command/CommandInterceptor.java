package com.zhougl.javadesignpatterndemo.process_engine.command;

/**
 * @author by zhougl
 * @classname CommandInterceptor
 * @desc TODO
 * @date 2019/6/9 16:04
 */
public interface CommandInterceptor {

    <T> T execute(Command<T> command);

    CommandInterceptor getNext();

    void setNext(CommandInterceptor next);
}
