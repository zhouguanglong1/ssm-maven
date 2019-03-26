package com.zhougl.singleton;

/**
 * 使用时，只需要SingleInstanceEnum.INSTANCE.fun();
 * @author zhougl
 * 2019/1/16
 **/
public enum SingleInstanceEnum {
    INSTANCE;
    public void fun(){
        System.out.println("fdsf");
    }
}
