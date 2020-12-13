package com.freedom.mst;

import java.util.Arrays;

/**
 * @author freedom
 * @date 2020/12/5 20:39
 */
public class Prim {

    private static final int NO_WAY = Integer.MAX_VALUE;

    public static final int NOT_VISITED = 0;
    public static final int VISITED = 1;

    // 图的结点个数
    private int verxs;
    // 存放结点数据
    private char[] data;
    // 存放边
    private int[][] weight;

    public Prim(char[] data, int[][] weight) {
        this.verxs = data.length;
        this.data = data;
        this.weight = weight;
    }

    public void showGraph() {
        for (int[] link : weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写普利姆算法，得到最小生成树
     *
     * @param v 表示从图的第几个顶点开始
     */
    public void prim(int v) {
        // 标记结点是否被访问过
        int[] visited = new int[verxs];
        // 把当前这个结点标记为已访问
        visited[v] = VISITED;
        // 记录两个结点的下标
        int h1 = -1;
        int h2 = -1;
        // 记录最小权值
        int minWeight = NO_WAY;

        for (int k = 1; k < verxs; k++) {
            for (int i = 0; i < verxs; i++) {
                for (int j = 0; j < verxs; j++) {
                    // i表示被访问过的结点，j表示没有被访问过的结点
                    if (visited[i] == VISITED && visited[j] == NOT_VISITED && minWeight > weight[i][j]) {
                        minWeight = weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 找到最小的一条边
            System.out.printf("边<%s, %s>的权值：%d\n", data[h1], data[h2], minWeight);
            // 把另外一条边记录为已访问
            visited[h2] = VISITED;
            minWeight = NO_WAY;
        }
    }
}
