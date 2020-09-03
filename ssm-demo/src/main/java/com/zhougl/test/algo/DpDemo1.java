package com.zhougl.test.algo;

/**
 * 8*8的格子，机器人需要从入口走到出口，每次只能往下或者往右走，粉色各自为障碍物，求机器人从入口到出口最短路径
 * <p>
 * 1.从上往下的暴力破解法：paths(start,end) = 1 + min(paths(start下方的格子,end), paths(start右边的格子,end))
 * <p>
 * 2.从下往上推，先确定填充最右边列的格子和最下面边的格子的最短路径，确定base case，状态转移方程式为：当前节点到出口的最短路径=1+min(右格子到出口最短路径,下方格子到出口最短路径)
 * <p>
 * 3.思考题：如果要记录走过的路径，应该如何实现？
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/9/2 12:57
 */
public class DpDemo1 {
    public static void main(String[] args) {
        int[][] inits = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, -1, 0, 0, 0, -1, 0},
                {0, 0, 0, 0, -1, 0, 0, 0},
                {-1, 0, -1, 0, 0, 1, 0, 0},
                {0, 0, -1, 0, 0, 0, 0, 0},
                {0, 0, 0, -1, -1, 0, -1, 0},
                {0, -1, 0, 0, 0, -1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        int minStep = getMinStep(inits);
        System.out.println(minStep);

    }

    private static int getMinStep(int[][] inits) {
        int n = 8;
        // 初始化右边列的格子
        for (int row = n - 2; row >= 0; row--) {
            if (inits[row][n - 1] == 0) {
                inits[row][n - 1] = inits[row + 1][n - 1] + 1;
            } else {
                inits[row][n - 1] = 100000;
            }
        }

        // 初始化底下边的格子
        for (int col = n - 2; col >= 0; col--) {
            if (inits[n - 1][col] == 0) {
                inits[n - 1][col] = inits[n - 1][col + 1] + 1;
            } else {
                inits[n - 1][col] = 100000;
            }
        }

        // 填充其他非障碍的节点
        for (int row = n - 2; row >= 0; row--) {
            for (int col = n - 2; col >= 0; col--) {
                if (inits[row][col] == -1) {
                    inits[row][col] = 100000;
                    continue;
                }
                inits[row][col] = 1 + Math.min(inits[row][col + 1], inits[row + 1][col]);
            }
        }
        return inits[0][0];
    }
}
