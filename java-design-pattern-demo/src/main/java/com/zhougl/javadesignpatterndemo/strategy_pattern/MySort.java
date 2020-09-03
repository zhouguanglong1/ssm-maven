package com.zhougl.javadesignpatterndemo.strategy_pattern;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/10/20 15:12
 */
public class MySort extends Sorter {
    @Override
    public void doSort(int[] listToSort) {
        getStrategy().sort(listToSort);
    }
}
