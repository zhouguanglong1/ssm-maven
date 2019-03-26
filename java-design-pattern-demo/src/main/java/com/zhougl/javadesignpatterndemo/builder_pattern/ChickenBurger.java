package com.zhougl.javadesignpatterndemo.builder_pattern;

/**
 * @author zhougl
 * 2019/2/18
 **/
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 35.5f;
    }


}
