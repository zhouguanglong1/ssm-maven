package com.zhougl.javadesignpatterndemo.command_pattern.demo2.command;

import com.zhougl.javadesignpatterndemo.command_pattern.demo2.receiver.Light;

/**
 * @author by zhougl
 * @classname LightOffCommand
 * @desc TODO
 * @date 2019/6/3 0:41
 */
public class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.lightOff();
    }
}
