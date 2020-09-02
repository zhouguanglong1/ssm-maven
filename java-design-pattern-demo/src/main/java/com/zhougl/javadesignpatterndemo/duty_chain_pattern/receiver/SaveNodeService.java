package com.zhougl.javadesignpatterndemo.duty_chain_pattern.receiver;

/**
 * @author by zhougl
 * @classname NodeService
 * @desc TODO
 * @date 2019/6/4 17:34
 */
public class SaveNodeService implements IReceiver {
    @Override
    public void doExecute() {
        System.out.println("新增节点。。。");
    }
}
