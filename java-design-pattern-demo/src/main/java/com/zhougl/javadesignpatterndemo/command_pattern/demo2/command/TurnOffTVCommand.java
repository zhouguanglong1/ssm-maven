package com.zhougl.javadesignpatterndemo.command_pattern.demo2.command;

import com.zhougl.javadesignpatterndemo.command_pattern.demo2.receiver.TV;

/**
 * @author by zhougl
 * @classname ChangeChannelCommand
 * @desc TODO
 * @date 2019/6/3 1:23
 */
public class TurnOffTVCommand implements Command {

    private TV tv;

    public TurnOffTVCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOffTV();
    }
}
