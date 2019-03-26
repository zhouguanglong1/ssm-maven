package com.zhougl.javadesignpatterndemo.builder_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhougl
 * 2019/2/18
 **/
public class Meal {
    public List<Item> items = new ArrayList<>();
    public void addItem(Item item){
        items.add(item);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "items=" + items +
                '}';
    }
}
