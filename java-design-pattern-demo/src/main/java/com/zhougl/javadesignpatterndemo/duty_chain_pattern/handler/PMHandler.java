package com.zhougl.javadesignpatterndemo.duty_chain_pattern.handler;

import com.zhougl.javadesignpatterndemo.duty_chain_pattern.bean.LeaveRequest;

/**
 * @author by zhougl
 * @classname PMHandler
 * @desc TODO
 * @date 2019/6/3 2:10
 */
public class PMHandler extends AbstractApproveHandler {

    @Override
    public void process(LeaveRequest leaveRequest) {
        //未填写姓名的请假单不通过
        if(null != leaveRequest.getName()){
            if(leaveRequest.getNumOfDays() <= 3){
                System.out.println(leaveRequest.getName()+",你通过项目经理审批!");
            }else {
                System.out.println("项目经理转交总经理");
                if(null != nextHandler){
                    nextHandler.process(leaveRequest);
                }
            }
        }else {
            System.out.println("请假单未填写完整,未通过项目经理审批!");
            return;
        }
    }
}
