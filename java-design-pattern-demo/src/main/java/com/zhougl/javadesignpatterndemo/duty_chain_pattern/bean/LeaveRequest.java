package com.zhougl.javadesignpatterndemo.duty_chain_pattern.bean;

/**
 * @author by zhougl
 * @classname LeaveRequest
 * @desc TODO
 * @date 2019/6/3 2:11
 */
public class LeaveRequest {
    // 请假人姓名
    private String name;
    // 请假天数
    private int numOfDays;
    //员工工龄(在公司大于2年则总经理会审批)
    private int workingAge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public int getWorkingAge() {
        return workingAge;
    }

    public void setWorkingAge(int workingAge) {
        this.workingAge = workingAge;
    }
}
