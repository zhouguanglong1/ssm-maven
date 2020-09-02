package com.zhougl.javadesignpatterndemo.duty_chain_pattern.command;

import com.zhougl.javadesignpatterndemo.duty_chain_pattern.receiver.IReceiver;

/**
 * @author by zhougl
 * @classname SaveDBCommand
 * @desc TODO
 * @date 2019/6/4 17:30
 */
public class SaveDBCommand implements Command {

    private IReceiver receiver;

    public SaveDBCommand(IReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.doExecute();
    }
}
