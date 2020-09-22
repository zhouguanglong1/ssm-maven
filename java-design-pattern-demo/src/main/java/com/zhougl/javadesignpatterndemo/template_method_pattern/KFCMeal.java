package com.zhougl.javadesignpatterndemo.template_method_pattern;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/10/20 15:01
 */
public class KFCMeal extends Lunch {
    @Override
    protected void prepareIngredients() {
        System.out.println("getting burger,cool drinks and etc...");
    }

    @Override
    protected void cooking() {
        System.out.println("I am cooking KFC Meal");
    }

    @Override
    public void eating() {
        System.out.println("hahaha ");
    }

    @Override
    protected void cleaning() {
        System.out.println("clean the plates");
    }
}
