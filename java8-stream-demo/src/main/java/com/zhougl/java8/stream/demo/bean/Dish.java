package com.zhougl.java8.stream.demo.bean;

/**
 * @author zhougl
 * @version v1.0.0
 * @date 2019/9/22 18:28
 */
public class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
