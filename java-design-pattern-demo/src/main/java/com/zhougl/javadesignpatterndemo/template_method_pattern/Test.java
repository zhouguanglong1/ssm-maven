package com.zhougl.javadesignpatterndemo.template_method_pattern;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/10/20 15:03
 */
public class Test {
    public static void main(String[] args) {
        Lunch meal1 = new McDonaldMeal();
        meal1.prepareLunch();
        Lunch meal2 = new KFCMeal();
        meal2.prepareLunch();
    }
}
