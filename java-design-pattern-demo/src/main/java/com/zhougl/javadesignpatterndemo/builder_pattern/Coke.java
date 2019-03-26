package com.zhougl.javadesignpatterndemo.builder_pattern;

/**
 * @author zhougl
 * 2019/2/18
 **/
public class Coke extends CodeDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 12.5f;
    }
}
