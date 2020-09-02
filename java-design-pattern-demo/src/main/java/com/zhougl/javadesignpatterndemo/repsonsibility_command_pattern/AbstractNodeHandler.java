package com.zhougl.javadesignpatterndemo.repsonsibility_command_pattern;

import com.zhougl.javadesignpatterndemo.duty_chain_pattern.bean.LeaveRequest;
import com.zhougl.javadesignpatterndemo.repsonsibility_command_pattern.bean.WorkflowActionParam;

/**
 * @author by zhougl
 * @classname AbstractNodeHandler
 * @desc TODO
 * @date 2019/6/4 15:46
 */
public abstract class AbstractNodeHandler {
    AbstractNodeHandler nextHandler;

    public void setNextHandler(AbstractNodeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract void process(WorkflowActionParam param);
}
