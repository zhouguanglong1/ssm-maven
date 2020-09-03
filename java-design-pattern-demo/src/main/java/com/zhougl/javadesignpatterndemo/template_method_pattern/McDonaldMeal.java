package com.zhougl.javadesignpatterndemo.template_method_pattern;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/10/20 14:59
 */
public class McDonaldMeal extends Lunch {
    @Override
    protected void prepareIngredients() {
        System.out.println("get chicken ,French fries etc...");
    }

    @Override
    protected void cooking() {
        System.out.println("I am cooking McDonald Meal!");
    }

    @Override
    protected void cleaning() {
        System.out.println("Clean plates and throwing away the paper plates");
    }
}
