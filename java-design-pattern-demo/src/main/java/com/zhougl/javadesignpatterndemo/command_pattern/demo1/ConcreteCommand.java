package com.zhougl.javadesignpatterndemo.command_pattern.demo1;

/**
 * @author by zhougl
 * @classname ConcreteCommand
 * @desc TODO
 * @date 2019/6/3 0:01
 */
public class ConcreteCommand implements Command {

    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }

    @Override
    public void undo() {
        receiver.unAction();
    }
}
