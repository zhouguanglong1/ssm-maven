package com.zhougl.javadesignpatterndemo.builder_pattern;

/**
 * @author zhougl
 * 2019/2/18
 **/
public class Pepsi extends CodeDrink {
    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public float price() {
        return 12.6f;
    }
}
