package com.zhougl.javadesignpatterndemo.builder_pattern;

/**
 * @author zhougl
 * 2019/2/18
 **/
public abstract class CodeDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
