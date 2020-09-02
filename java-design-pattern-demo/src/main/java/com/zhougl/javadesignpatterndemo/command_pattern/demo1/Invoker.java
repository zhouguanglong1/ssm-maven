package com.zhougl.javadesignpatterndemo.command_pattern.demo1;

/**
 * @author by zhougl
 * @classname Invoker
 * @desc TODO
 * @date 2019/6/3 0:03
 */
public class Invoker {
    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand(){
        command.execute();
    }

    public void undoCommand(){
        command.undo();
    }
}
