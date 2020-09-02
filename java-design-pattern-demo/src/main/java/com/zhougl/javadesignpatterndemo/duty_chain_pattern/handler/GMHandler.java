package com.zhougl.javadesignpatterndemo.duty_chain_pattern.handler;

import com.zhougl.javadesignpatterndemo.duty_chain_pattern.bean.LeaveRequest;

/**
 * @author by zhougl
 * @classname PMHandler
 * @desc TODO
 * @date 2019/6/3 2:10
 */
public class GMHandler extends AbstractApproveHandler {

    @Override
    public void process(LeaveRequest leaveRequest) {
        //员工在公司工龄超过2年,则审批通过
        if(leaveRequest.getWorkingAge() >=2 && leaveRequest.getNumOfDays() > 3){
            System.out.println(leaveRequest.getName()+",你通过总经理审批!");
            if(null != nextHandler){
                nextHandler.process(leaveRequest);
            }
        }else {
            System.out.println("在公司年限不够,长假未通过总经理审批!");
            return;
        }
    }
}
