package com.zhougl.javadesignpatterndemo.command_pattern.demo2;

import com.zhougl.javadesignpatterndemo.command_pattern.demo2.command.*;
import com.zhougl.javadesignpatterndemo.command_pattern.demo2.invoker.XiaoAI;
import com.zhougl.javadesignpatterndemo.command_pattern.demo2.receiver.Light;
import com.zhougl.javadesignpatterndemo.command_pattern.demo2.receiver.TV;

/**
 * @author by zhougl
 * @classname CommandPatternClient
 * @desc TODO
 * @date 2019/6/3 0:45
 */
public class CommandPatternClient {
    public static void main(String[] args) {
        // Receiver
        Light light = new Light();
        TV tv = new TV();
        Command lightOnCommand = new LightOnCommand(light);
        Command lightOffCommand = new LightOffCommand(light);
        Command turnOnTVCommand = new TurnOnTVCommand(tv);
        Command turnOffTVCommand = new TurnOffTVCommand(tv);
        Command changeChannelCommand = new ChangeChannelCommand(tv);
        // Invoker 小爱同学
        XiaoAI xiaoAI = new XiaoAI();
        System.out.println("小爱同学，开灯,顺便打开电视机，切换到CCTV5");
        xiaoAI.setCommand(lightOnCommand);
        xiaoAI.execute();
        xiaoAI.setCommand(turnOnTVCommand);
        xiaoAI.execute();
        xiaoAI.setCommand(changeChannelCommand);
        xiaoAI.execute();
        System.out.println("小爱同学，关灯,并关闭电视机");
        xiaoAI.setCommand(lightOffCommand);
        xiaoAI.execute();
        xiaoAI.setCommand(turnOffTVCommand);
        xiaoAI.execute();
    }
}
