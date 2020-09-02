package com.zhougl.javadesignpatterndemo.command_pattern.demo2.invoker;

import com.zhougl.javadesignpatterndemo.command_pattern.demo2.command.Command;

/**
 * @author by zhougl
 * @classname XiaoAI
 * @desc Receiver类
 * @date 2019/6/3 0:37
 */
public class XiaoAI {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute(){
        command.execute();
    }
}
