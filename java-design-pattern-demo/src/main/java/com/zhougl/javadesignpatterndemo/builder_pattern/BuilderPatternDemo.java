package com.zhougl.javadesignpatterndemo.builder_pattern;

/**
 * @author zhougl
 * 2019/2/18
 **/
public class BuilderPatternDemo {
    public static void main(String[] args){
        MealBuilder builder = new MealBuilder();
        Meal meal = builder.prepareNoVegBurger();
        Meal meal1 = builder.prepareVegBurger();
        System.out.println(meal);
        System.out.println(meal1);
    }
}
