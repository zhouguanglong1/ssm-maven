package com.zhougl.javadesignpatterndemo.duty_chain_pattern;

import com.zhougl.javadesignpatterndemo.duty_chain_pattern.bean.LeaveRequest;
import com.zhougl.javadesignpatterndemo.duty_chain_pattern.handler.GMHandler;
import com.zhougl.javadesignpatterndemo.duty_chain_pattern.handler.PMHandler;

/**
 * @author by zhougl
 * @classname Test
 * @desc TODO
 * @date 2019/6/3 2:17
 */
public class Test {
    public static void main(String[] args) {
        PMHandler pm = new PMHandler();
        GMHandler gm = new GMHandler();

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setName("张三");
        leaveRequest.setNumOfDays(4);//请假4天
        leaveRequest.setWorkingAge(3);//工龄3年

        pm.setNextHandler(gm);//设置传递顺序
        pm.process(leaveRequest);
    }
}
