package com.freedom.mst;

/**
 * @author freedom
 * @date 2020/12/5 20:39
 *
 */
public class MSTTest {

    private static final int NO_WAY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵的关系使用二维数组表示
        int[][] weight = {
                {NO_WAY, 5, 7, NO_WAY, NO_WAY, NO_WAY, 2},
                {5, NO_WAY, NO_WAY, 9, NO_WAY, NO_WAY, 3},
                {7, NO_WAY, NO_WAY, NO_WAY, 8, NO_WAY, NO_WAY},
                {NO_WAY, 9, NO_WAY, NO_WAY, NO_WAY, 4, NO_WAY},
                {NO_WAY, NO_WAY, 8, NO_WAY, NO_WAY, 5, 4},
                {NO_WAY, NO_WAY, NO_WAY, 4, 5, NO_WAY, 6},
                {2, 3, NO_WAY, NO_WAY, 4, 6, NO_WAY}};

        System.out.println("-----------------------普利姆算法生成最小生成树-----------------------");
        Prim prim = new Prim(data, weight);
        prim.showGraph();
        System.out.println("路线连通：");
        prim.prim(0);

        System.out.println("-----------------------克鲁斯卡尔算法生成最小生成树-----------------------");
        Kruskal kruskal = new Kruskal(data, weight);
        kruskal.showGraph();
        kruskal.kruskal();
    }
}
