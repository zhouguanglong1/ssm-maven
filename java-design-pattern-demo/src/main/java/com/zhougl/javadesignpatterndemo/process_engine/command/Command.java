package com.zhougl.javadesignpatterndemo.process_engine.command;

/**
 * @author by zhougl
 * @classname Command
 * @desc TODO
 * @date 2019/6/9 16:03
 */
public interface Command<T> {
    T execute(CommandContext commandContext);
}
