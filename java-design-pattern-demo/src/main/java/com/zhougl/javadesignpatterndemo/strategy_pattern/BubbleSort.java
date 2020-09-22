package com.zhougl.javadesignpatterndemo.strategy_pattern;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/10/20 15:09
 */
public class BubbleSort implements SortInterface {
    @Override
    public void sort(int[] array) {
        System.out.println("bubble sort");
    }
}
