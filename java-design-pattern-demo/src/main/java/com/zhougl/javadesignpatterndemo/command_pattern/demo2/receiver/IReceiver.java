package com.zhougl.javadesignpatterndemo.command_pattern.demo2.receiver;

/**
 * @author by zhougl
 * @classname IReceiver
 * @desc 可以把TV打开关闭切换频道等动作，都单独成一个Receiver类，这样在具体命令类中，只需要直接调用IReceiver.doExecute()
 * @date 2019/6/3 1:51
 */
public interface IReceiver {
    void doExecute();
}
