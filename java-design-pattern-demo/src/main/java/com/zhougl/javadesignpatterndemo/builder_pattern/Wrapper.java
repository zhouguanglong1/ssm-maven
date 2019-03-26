package com.zhougl.javadesignpatterndemo.builder_pattern;

/**
 * @author zhougl
 * 2019/2/18
 **/
public class Wrapper implements Packing {
    @Override
    public String packing() {
        return "wrapper";
    }
}
