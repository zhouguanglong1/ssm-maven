package com.zhougl.javadesignpatterndemo.command_pattern.demo2.receiver;

/**
 * @author by zhougl
 * @classname TV
 * @desc TODO
 * @date 2019/6/3 1:21
 */
public class TV {
    public void changeChannel(){
        System.out.println("切换到CCTV5");
    }

    public void turnOnTV(){
        System.out.println("打开电视");
    }

    public void turnOffTV(){
        System.out.println("关闭电视");
    }
}
