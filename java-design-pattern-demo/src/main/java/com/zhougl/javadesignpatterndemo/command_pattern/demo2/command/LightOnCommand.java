package com.zhougl.javadesignpatterndemo.command_pattern.demo2.command;

import com.zhougl.javadesignpatterndemo.command_pattern.demo2.receiver.Light;

/**
 * @author by zhougl
 * @classname LightOnCommand
 * @desc TODO
 * @date 2019/6/3 0:40
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.lightOn();
    }
}
