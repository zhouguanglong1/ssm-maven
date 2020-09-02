package com.zhougl.javadesignpatterndemo.command_pattern.demo1;

/**
 * @author by zhougl
 * @classname Receiver
 * @desc TODO
 * @date 2019/6/3 0:02
 */
public class Receiver {
    public void action(){
        System.out.println("执行一个命令");
    }

    public void unAction(){
        System.out.println("撤销一个命令");
    }
}
