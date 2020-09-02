package com.zhougl.javadesignpatterndemo.duty_chain_pattern.command;

/**
 * @author by zhougl
 * @classname SaveDBCommand
 * @desc TODO
 * @date 2019/6/4 17:30
 */
public class UpdateDBCommand implements Command {



    @Override
    public void execute() {
        System.out.println("----------------数据库修改成功---------------");
    }
}
