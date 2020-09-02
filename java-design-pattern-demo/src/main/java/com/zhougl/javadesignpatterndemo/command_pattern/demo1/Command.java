package com.zhougl.javadesignpatterndemo.command_pattern.demo1;

/**
 * @author by zhougl
 * @classname Command
 * @desc TODO
 * @date 2019/6/2 23:47
 */
public interface Command {
    void execute();
    void undo();
}
