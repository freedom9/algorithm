package com.freedom.mst;

import java.util.Arrays;

/**
 * @author freedom
 * @date 2020/12/6 21:14
 * @description 1、基本思想：按照权值从小到大的顺序选择n-1条边，并保证n-1条边不构造回路。
 *              2、判断是否形成回路的方式：记录顶点在“最小生成树”中的终点，顶点的终点是“在最小生成树中与它连通的最大顶点”。
 *                 然后每次需要将一条边添加到最小生存树时，判断该边的两个顶点的终点是否重回，重回的话则会构成回路。
 */
public class Kruskal {

    private static final int NO_WAY = Integer.MAX_VALUE;

    // 边的个数
    private int edgeNum;
    // 存放结点数据
    private char[] data;
    // 存放边
    private int[][] weight;

    public Kruskal(char[] data, int[][] weight) {
        this.data = data;
        this.weight = weight;

        for (int i = 0; i < weight.length; i++) {
            for (int j = i + 1; j < weight[i].length; j++) {
                if (NO_WAY != weight[i][j]) {
                    edgeNum++;
                }
            }
        }
    }

    public void showGraph() {
        for (int[] link : weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void kruskal() {
        // 表示最后结果数组的索引
        int index = 0;
        // 用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        // 创建结果数组，保存最后的最小生成树
        EData[] res = new EData[edgeNum];

        // 获取图中所有边的集合
        EData[] edges = getEdges();

        sortEdges(edges);

        // 遍历edges数组，将边添加到最小生成树中时，判断是准备加入的边是否形成了回路，如果没有，就加入到res数组
        for (int i = 0; i < edgeNum; i++) {
            // 获取到边的一个顶点
            int p1 = getPosition(edges[i].start);
            // 获取到边的另一个顶点
            int p2 = getPosition(edges[i].end);

            // 获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            // 获取p2这个顶点在已有最小生成树中的终点
            int n = getEnd(ends, p2);
            // 没有构成回路
            if (m != n) {
                ends[m] = n;
                res[index++] = edges[i];
            }
        }

        System.out.println("最小生成树：");
        for (int i = 0; i < index; i++) {
            System.out.printf("边<%s, %s>的权值：%d\n", res[i].start, res[i].end, res[i].weight);
        }
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends
     * @param i
     * @return
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    /**
     * 根据顶点值获取data对应的下标
     *
     * @param ch
     * @return
     */
    private int getPosition(char ch) {
        for (int i = 0; i < data.length; i++) {
            if (ch == data[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 使用希尔排序对数组排序
     *
     * @param edges
     */
    private void sortEdges(EData[] edges) {
        int index = 0;
        EData temp;
        for (int gap = edges.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < edges.length; i++) {
                index = i;
                temp = edges[i];

                if (edges[index].weight < edges[index - gap].weight) {
                    while (index - gap >= 0 && temp.weight < edges[index - gap].weight) {
                        edges[index] = edges[index - gap];
                        index -= gap;
                    }
                    edges[index] = temp;
                }
            }
        }
    }

    /**
     * 获取图中的边，放到EData[]数组中
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < weight.length; i++) {
            for (int j = i + 1; j < weight[i].length; j++) {
                if (NO_WAY != weight[i][j]) {
                    edges[index++] = new EData(data[i], data[j], weight[i][j]);
                }
            }
        }
        return edges;
    }

    private class EData {
        // 边的一个点
        private char start;
        // 边的另外一个点
        private char end;
        // 边的权值
        private int weight;

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EData{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }
    }
}
