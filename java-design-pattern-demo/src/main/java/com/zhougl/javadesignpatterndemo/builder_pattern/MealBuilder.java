package com.zhougl.javadesignpatterndemo.builder_pattern;

/**
 * @author zhougl
 * 2019/2/18
 **/
public class MealBuilder {
    public Meal prepareVegBurger(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareNoVegBurger(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }
}
