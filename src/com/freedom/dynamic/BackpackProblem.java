package com.freedom.dynamic;

/**
 * @author freedom
 * @date 2020/11/28 21:35
 * @description 1、动态规划（Dynamic Programming）算法的核心思想：将大问题划分为小问题进行解决，从而一步步获取最优解。
 *              2、动态规划算法与分治算法类似，其基本思想也是将待求解问题分解成若干子问题，先求解子问题，然后从这些子
 *                 问题的解得到原问题的解。但与分治法不同的是，适用于动态规划求解的问题，经分解得到子问题往往不是互相
 *                 独立的（即下一个子阶段的求解是建立在上一个子阶段的解的基础上，进行下一步的求解）。
 *              3、动态规划可以通过填表的方式来逐步推进，得到最优解。
 */
public class BackpackProblem {

    public static void main(String[] args) {
        // 物品的重量
        int[] weight = {1, 4, 3};
        // 物品的价值，val[i]的就是weight[i]的价值
        int[] val = {1500, 3000, 2000};
        // 背包的容量
        int capacity = 4;

        int number = val.length;
        // v[i][j]表示在前i个物品中能够装入容易为j的背包中的最大价值
        int[][] maxValue = new int[number + 1][capacity + 1];
        // 为了记录放入商品的情况
        int[][] path = new int[number + 1][capacity + 1];

        // 初始化第一行和第一列，由于数组初始值为0，可不做操作
        for (int i = 0; i < maxValue.length; i++) {
            maxValue[i][0] = 0;
        }
        for (int i = 0; i < maxValue[0].length; i++) {
            maxValue[0][i] = 0;
        }

        for (int i = 1; i < maxValue.length; i++) {
            for (int j = 1; j < maxValue[0].length; j++) {
                if (weight[i - 1] > j) {
                    maxValue[i][j] = maxValue[i - 1][j];
                } else {
//                    maxValue[i][j] = Math.max(maxValue[i - 1][j], val[i - 1] + maxValue[i - 1][j - weight[i - 1]]);
                    // 为了记录商品存放到背包的情况，需要if-else来体现公式
                    if (maxValue[i - 1][j] < val[i -1] + maxValue[i - 1][j - weight[i -1]]) {
                        maxValue[i][j] = val[i - 1] + maxValue[i -1][j - weight[i - 1]];
                        path[i][j] = 1;
                    } else {
                        maxValue[i][j] = maxValue[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < maxValue.length; i++) {
            for (int j = 0; j < maxValue[i].length; j++) {
                System.out.print(maxValue[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("-------------------------------------------");
        // 输出最后放入哪些商品价值最大
        int i = path.length - 1;
        int j = path[0].length - 1;
        // 从path的最后开始找
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= weight[i -1];
            }
            i--;
        }
    }
}
