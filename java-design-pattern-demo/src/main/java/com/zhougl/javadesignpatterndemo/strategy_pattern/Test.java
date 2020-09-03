package com.zhougl.javadesignpatterndemo.strategy_pattern;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/10/20 15:12
 */
public class Test {
    public static void main(String[] args) {
        int[] list = {18,26,1,5};
        MySort mySort = new MySort();
        mySort.setStrategy(new QuickSort());
        mySort.doSort(list);
        mySort.setStrategy(new BubbleSort());
        mySort.doSort(list);
    }
}
