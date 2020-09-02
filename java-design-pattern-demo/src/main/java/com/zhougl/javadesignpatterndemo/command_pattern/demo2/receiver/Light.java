package com.zhougl.javadesignpatterndemo.command_pattern.demo2.receiver;

/**
 * @author by zhougl
 * @classname Light
 * @desc Invoker类
 * @date 2019/6/3 0:39
 */
public class Light {

    public void lightOn(){
        System.out.println("开灯");
    }

    public void lightOff(){
        System.out.println("关灯");
    }
}
