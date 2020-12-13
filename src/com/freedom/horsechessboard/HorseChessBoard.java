package com.freedom.horsechessboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author freedom
 * @date 2020/12/13 20:34
 */
public class HorseChessBoard {
    // 棋盘的列数
    private int X;
    // 棋盘的行数
    private int Y;
    // 棋盘
    private int[][] chessBoard;
    // 标记棋盘的各个位置是否被访问过
    private boolean[] visited;
    // 标记是否棋盘的所以位置都被访问
    private boolean finished;

    public HorseChessBoard(int X, int Y) {
        this.X = X;
        this.Y = Y;
        chessBoard = new int[X][Y];
        visited = new boolean[X * Y];
    }

    /**
     * @param row    当前的位置的行，从0开始
     * @param column 当前的位置的列，从0开始
     * @param step   第几步，从第一步开始
     */
    public void traversalChessBoard(int row, int column, int step) {
        chessBoard[row][column] = step;
        visited[row * X + column] = true;
        // 获取当前位置可以走的下一个位置的集合
        List<Point> ps = next(new Point(column, row));
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            if (!visited[p.y * X + p.x]) {
                // 没有访问过
                traversalChessBoard(p.y, p.x, step + 1);
            }
        }

        // 如果没有达到数目，则表示没有完成任务，重置棋盘
        // step < X * Y，有两种情况：
        // 1、棋盘到目标位置，仍然没有走完
        // 2、棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置，计算马儿还能走哪些位置，并放入到一个集合中，最多有8个位置   *
     *
     * @param curPoint
     * @return
     */
    private List<Point> next(Point curPoint) {
        List<Point> ps = new ArrayList<>();
        Point p = new Point();
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p));
        }
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p));
        }
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p));
        }
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p));
        }
        if ((p.x = curPoint.x + 2) < X && (p.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p));
        }
        if ((p.x = curPoint.x + 1) < X && (p.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p));
        }
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p));
        }
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p));
        }
        return ps;
    }

    /**
     * 根据当前这步的所有的下一步的选择位置，进行非递减排序，减少回溯的次数（贪心算法）
     *
     * @param ps
     */
    private void sort(List<Point> ps) {
        ps.sort((o1, o2) -> {
            int count1 = next(o1).size();
            int count2 = next(o2).size();
            if (count1 < count2) {
                return -1;
            } else if (count1 > count2) {
                return 1;
            } else {
                return 0;
            }
        });
    }

    public void show() {
        for (int[] rows : chessBoard) {
            System.out.println(Arrays.toString(rows));
        }
    }
}
