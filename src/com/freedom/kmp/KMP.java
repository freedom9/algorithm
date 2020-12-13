package com.freedom.kmp;

/**
 * @author freedom
 * @date 2020/11/29 21:08
 */
public class KMP {

    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配值表
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 需要处理str1.charAt(i) != str2.charAt(j)，调整j的大小
            // KMP算法核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取一个字符串（子串）的部分匹配值表
     * 部分匹配值表：“前缀”和“后缀”的最长的共有元素的长度。
     * 字符串：”bread”
     * 前缀：b、br、bre、brea
     * 后缀：read、ead、ad、d
     *
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        // 如果字符串的长度为1，部分匹配值就是0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当dest.charAt(i) != dest.charAt(j),我们需要从next[j -1]获取新的j
            // 这是kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
