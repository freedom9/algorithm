package com.freedom.shortestpath;

import java.util.Arrays;

/**
 * @author freedom
 * @date 2020/12/11 20:36
 * @description: 1、弗洛伊德算法（Floyd）计算图中各个顶点之间的最短路径。
 *               2、弗洛伊德算法 VS 迪杰斯特拉算法
 *                  2.1 迪杰斯特拉算法通过选定的被访问顶点，求出从出发访问顶点到其他顶点的最短路径；
 *                  2.2 弗洛伊德算法中每一个顶点都是出发访问点，所以需要将每一个顶点看作被访问顶点，
 *                      求出从每一个顶点到其他顶点的最短路径。
 */
public class Floyd {

    // 存放顶点
    private char[] vertices;
    // 从各个结点出发到其他结点的距离
    private int[][] distance;
    // 保存到达目标顶点的前驱顶点
    private int[][] preVertex;

    public Floyd(char[] vertices, int[][] weight) {
        this.vertices = vertices;
        this.distance = weight;
        this.preVertex = new int[vertices.length][vertices.length];
        // 对preVertex数组初始化，注意存放的是前驱顶点的下标
        for (int i = 0; i < vertices.length; i++) {
            Arrays.fill(preVertex[i], i);
        }
    }

    public void floyd() {
        int len = 0;
        // 对中间顶点遍历
        for (int i = 0; i < vertices.length; i++) {
            // 从j顶点出发
            for (int j = 0; j < vertices.length; j++) {
                // 到达k顶点
                for (int k = 0; k < vertices.length; k++) {
                    // 从j顶点出发，经过i顶点，到达k顶点的距离
                    len = distance[j][i] + distance[i][k];
                    if (len < distance[j][k]) {
                        distance[j][k] = len;
                        // 更新前驱结点
                        preVertex[j][k] = preVertex[i][k];
                    }
                }
            }
        }
    }

    public void show(char[] vertices) {
        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                System.out.printf("%d(%s->%s)   ", distance[i][j], vertices[i], vertices[j]);
            }
            System.out.println();
        }
    }
}
