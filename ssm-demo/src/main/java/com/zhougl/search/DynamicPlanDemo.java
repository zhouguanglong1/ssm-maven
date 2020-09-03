package com.zhougl.search;

/**
 * 动态规划例子
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/1/17 16:08
 */
public class DynamicPlanDemo {
    public int knapsack(int[] weight, int n, int w) {
        // 默认值 false
        boolean[][] states = new boolean[n][w + 1];
        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][0] = true;
        states[0][weight[0]] = true;
        // 动态规划状态转移
        for (int i = 1; i < n; ++i) {
            // 不把第 i 个物品放入背包
            for (int j = 0; j <= w; ++j) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j];
                }
            }
            // 把第 i 个物品放入背包
            for (int j = 0; j <= w - weight[i]; ++j) {
                if (states[i - 1][j]) {
                    states[i][j + weight[i]] = true;
                }
            }
        }
        // 输出结果
        for (int i = w; i >= 0; --i) {
            if (states[n - 1][i]) {
                return i;
            }
        }
        return 0;
    }

    public int knapsack2(int[] items, int n, int w) {
        // 默认值 false
        boolean[] states = new boolean[w + 1];
        // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0] = true;
        states[items[0]] = true;
        // 动态规划
        for (int i = 1; i < n; ++i) {
            for (int j = w - items[i]; j >= 0; --j) {
                // 把第 i 个物品放入背包
                if (states[j]) {
                    states[j + items[i]] = true;
                }
            }
        }
        // 输出结果
        for (int i = w; i >= 0; --i) {
            if (states[i]) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        DynamicPlanDemo planDemo = new DynamicPlanDemo();
        int[] weights = {2, 2, 4, 6, 3};
        System.out.println(planDemo.knapsack2(weights, 5, 16));
    }
}
