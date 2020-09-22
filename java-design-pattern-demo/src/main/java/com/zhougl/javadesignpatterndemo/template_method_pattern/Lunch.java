package com.zhougl.javadesignpatterndemo.template_method_pattern;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/10/20 14:55
 */
public abstract class Lunch {

    public final void prepareLunch(){
        prepareIngredients();
        cooking();
        eating();
        cleaning();

    }

    protected abstract void prepareIngredients();
    protected abstract void cooking();
    public void eating(){
        System.out.println("I’m eating,please do not disturb me");
    }
    protected abstract void cleaning();
}
