package com.freedom.horsechessboard;

/**
 * @author freedom
 * @date 2020/12/13 21:16
 */
public class HorseChessBoardTest {

    public static void main(String[] args) {
        // 开始位置的行
        int row = 1;
        int column = 1;
        HorseChessBoard horseChessBoard = new HorseChessBoard(8, 8);
        long start = System.currentTimeMillis();
        horseChessBoard.traversalChessBoard(row - 1, column - 1, 1);
        System.out.printf("耗时：%d\n", System.currentTimeMillis() - start);

        System.out.println("最后的结果：");
        horseChessBoard.show();
    }
}
