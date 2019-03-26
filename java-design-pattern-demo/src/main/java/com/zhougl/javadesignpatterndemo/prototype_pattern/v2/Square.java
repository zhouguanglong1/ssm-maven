package com.zhougl.javadesignpatterndemo.prototype_pattern.v2;

/**
 * @author zhougl
 * 2019/3/17
 **/
public class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
