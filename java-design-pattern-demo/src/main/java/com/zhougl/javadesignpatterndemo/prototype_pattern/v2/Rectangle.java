package com.zhougl.javadesignpatterndemo.prototype_pattern.v2;

/**
 * @author zhougl
 * 2019/3/17
 **/
public class Rectangle extends Shape {

    public Rectangle() {
        type = "Rectangle";
    }

    @Override
    void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
