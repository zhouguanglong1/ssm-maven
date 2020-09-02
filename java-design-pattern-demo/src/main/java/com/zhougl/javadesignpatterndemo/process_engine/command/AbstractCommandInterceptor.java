package com.zhougl.javadesignpatterndemo.process_engine.command;

/**
 * @author by zhougl
 * @classname AbstractCommandInterceptor
 * @desc TODO
 * @date 2019/6/9 16:06
 */
public abstract class AbstractCommandInterceptor implements CommandInterceptor {
    protected CommandInterceptor next;

    @Override
    public CommandInterceptor getNext() {
        return next;
    }

    @Override
    public void setNext(CommandInterceptor next) {
        this.next = next;
    }
}
