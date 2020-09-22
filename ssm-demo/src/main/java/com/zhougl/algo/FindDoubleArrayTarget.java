package com.zhougl.algo;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/5/22 13:12
 */
public class FindDoubleArrayTarget {
    public boolean Find(int target, int[][] array) {
        int row = array.length;
        int col = array[0].length;
        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            if (array[i][j] < target) {
                i++;
            } else if (array[i][j] > target) {
                j--;
            } else {
                return true;
            }
        }
        return false;
    }
}
