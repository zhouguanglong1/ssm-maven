package com.zhougl.javadesignpatterndemo.builder_pattern;

/**
 * @author zhougl
 * 2019/2/18
 **/
public interface Item {
    String name();
    Packing packing();
    float price();
}
