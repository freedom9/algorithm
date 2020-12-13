package com.freedom.hanoitower;

/**
 * @author freedom
 * @date 2020/11/26 20:43
 */
public class HanoiTower {

    /**
     * @param num 盘中个数
     * @param a 盘移之前所在的塔
     * @param b 辅助的塔
     * @param c 盘移之后所在的塔
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "—>" + c);
        } else {
            // 如果有num > 1情况，我们总是可以看做是两个盘 1、最下面的一个盘  2、上面得所有的盘
            // 1、先把最上面的所有盘A->B，移动过程会使用到C塔
            hanoiTower(num - 1, a, c, b);
            // 2、把最下面的盘A->C
            System.out.println("第" + num + "个盘从 " + a + "—>" + c);
            // 3、把B塔的所有的盘从B->C，移动过程使用到A塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
