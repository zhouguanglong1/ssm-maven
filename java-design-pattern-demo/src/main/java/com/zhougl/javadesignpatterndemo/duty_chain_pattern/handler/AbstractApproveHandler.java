package com.zhougl.javadesignpatterndemo.duty_chain_pattern.handler;

import com.zhougl.javadesignpatterndemo.duty_chain_pattern.bean.LeaveRequest;

/**
 * @author by zhougl
 * @classname ApproveHandler
 * @desc 员工在OA系统中提交请假申请，首先项目经理处理，他能审批3天以内的假期，
 * 如果大于3天，则由项目经理则转交给总经理处理。接下来我们用责任链模式实现这个过程。
 * @date 2019/6/3 2:08
 */
public abstract class AbstractApproveHandler {
    AbstractApproveHandler nextHandler;



    public void setNextHandler(AbstractApproveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected abstract void process(LeaveRequest leaveRequest);
}
