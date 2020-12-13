package com.freedom.shortestpath;

import java.util.Arrays;

/**
 * @author freedom
 * @date 2020/12/8 21:05
 * @description 迪杰斯特拉（Dijkstra）算法是典型最短路径算法，用于计算一个结点到其他结点的最短路径。它的主要特点是以
 *              起始点为中心向外层层扩展（广度优先搜索思想），直到扩展到终点为止。
 */
public class Dijkstra {

    // 顶点数组
    private char[] vertices;
    // 邻接矩阵
    private int[][] weight;
    // 已经访问的顶点的集合
    private VisitedVertices vv;

    public Dijkstra(char[] vertices, int[][] weight) {
        this.vertices = vertices;
        this.weight = weight;
    }

    public void showGraph() {
        for (int[] link : weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * @param index 出发顶点对应的下标
     */
    public void dijkstra(int index) {
        vv = new VisitedVertices(vertices.length, index);
        update(index);

        for (int i = 1; i < vertices.length; i++) {
            // 选择新的访问顶点
            index = vv.selectNewIndex();
            update(index);
        }
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
     *
     * @param index 顶点下标
     */
    private void update(int index) {
        int len = 0;
        for (int j = 0; j < weight[index].length; j++) {
            // 出发顶点到index顶点的距离 + 从index顶点到j顶点的距离
            len = vv.getDis(index) + weight[index][j];
            // 判断j顶点未被访问过，并且len小于出发顶点到j顶点的距离
            if (vv.notVisited(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);
                vv.updateDis(j, len);
            }
        }
    }

    public void showDijkstra(char[] vertices) {
        vv.showDijkstra(vertices);
    }

    private class VisitedVertices {

        public static final int NOT_VISITED = 0;
        public static final int VISITED = 1;

        private static final int NO_WAY = 65535;

        // 各个顶点是否访问过，1：表示访问过，0：未访问
        private int[] whetherVisited;

        // 每个下标对应的值为前一个顶点下标
        private int[] preVertexIndex;

        // 记录出发顶点到其他顶点的距离
        private int[] distance;

        /**
         * @param length 顶点个数
         * @param index  出发顶点对应的下标
         */
        public VisitedVertices(int length, int index) {
            this.whetherVisited = new int[length];
            this.preVertexIndex = new int[length];
            this.distance = new int[length];

            Arrays.fill(distance, NO_WAY);
            // 设置出发顶点被访问过
            this.whetherVisited[index] = VISITED;
            this.distance[index] = 0;
        }

        /**
         * 获取出发顶点到index顶点的距离
         *
         * @param index
         * @return
         */
        public int getDis(int index) {
            return distance[index];
        }

        /**
         * 判断index顶点是否未访问过
         *
         * @param index
         * @return
         */
        public boolean notVisited(int index) {
            return whetherVisited[index] == NOT_VISITED;
        }

        /**
         * 更新index顶点的前驱顶点为pre顶点
         *
         * @param index
         * @param pre
         */
        public void updatePre(int index, int pre) {
            preVertexIndex[index] = pre;
        }

        /**
         * 更新出发顶点到index顶点的距离
         *
         * @param index
         * @param len
         */
        public void updateDis(int index, int len) {
            distance[index] = len;
        }

        /**
         * 获取新的访问顶点
         *
         * @return
         */
        public int selectNewIndex() {
           int min = NO_WAY;
           int index = 0;
            for (int i = 0; i < whetherVisited.length; i++) {
                if (whetherVisited[i] == NOT_VISITED && distance[i] < min) {
                    min= distance[i];
                    index =i;
                }
            }
            whetherVisited[index] = VISITED;
            return index;
        }

        public void showDijkstra(char[] vertices) {
            for (int i = 0; i < distance.length; i++) {
                if (distance[i] != NO_WAY) {
                    System.out.print(vertices[i] + "(" + distance[i] + ")\t");
                } else {
                    System.out.print(vertices[i] + "(N)\t");
                }
            }
        }
    }
}
