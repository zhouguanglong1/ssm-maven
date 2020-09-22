package com.zhougl.javadesignpatterndemo.strategy_pattern;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/10/20 15:10
 */
public abstract class Sorter {
    private SortInterface strategy;

    public SortInterface getStrategy() {
        return strategy;
    }

    public void setStrategy(SortInterface strategy) {
        this.strategy = strategy;
    }

    public abstract void doSort(int[] listToSort);
}
