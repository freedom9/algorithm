package com.freedom.shortestpath;

/**
 * @author freedom
 * @date 2020/12/8 21:05
 */
public class ShortestPathTest {

    private static final int NO_WAY = 65535;

    public static void main(String[] args) {
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵的关系使用二维数组表示
        int[][] weight = {
                {0, 5, 7, NO_WAY, NO_WAY, NO_WAY, 2},
                {5, 0, NO_WAY, 9, NO_WAY, NO_WAY, 3},
                {7, NO_WAY, 0, NO_WAY, 8, NO_WAY, NO_WAY},
                {NO_WAY, 9, NO_WAY, 0, NO_WAY, 4, NO_WAY},
                {NO_WAY, NO_WAY, 8, NO_WAY, 0, 5, 4},
                {NO_WAY, NO_WAY, NO_WAY, 4, 5, 0, 6},
                {2, 3, NO_WAY, NO_WAY, 4, 6, 0}};

        System.out.println("-----------------迪杰斯特拉算法-----------------");
        Dijkstra dijkstra = new Dijkstra(vertices, weight);
        dijkstra.showGraph();
        dijkstra.dijkstra(3);
        dijkstra.showDijkstra(vertices);

        System.out.println();
        System.out.println("-----------------弗洛伊德算法-----------------");
        Floyd floyd = new Floyd(vertices, weight);
        floyd.floyd();
        floyd.show(vertices);
    }
}
