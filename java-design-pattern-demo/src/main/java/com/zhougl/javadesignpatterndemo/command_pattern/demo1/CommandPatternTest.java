package com.zhougl.javadesignpatterndemo.command_pattern.demo1;

/**
 * @author by zhougl
 * @classname CommandPatternTest
 * @desc TODO
 * @date 2019/6/3 0:16
 */
public class CommandPatternTest {
    public static void main(String[] args) {

        Command command = new ConcreteCommand(new Receiver());

        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.executeCommand();
        invoker.undoCommand();
    }
}
