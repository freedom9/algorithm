package com.freedom.kmp;

import java.util.Arrays;

/**
 * @author freedom
 * @date 2020/11/29 20:59
 */
public class KMPTest {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.printf("暴力匹配，index = %d\n", ViolentMatch.violentMatch(str1, str2));

        int[] next = KMP.kmpNext(str2);
        System.out.println(Arrays.toString(next));

        System.out.printf("KMP匹配, index = %d\n", KMP.kmpSearch(str1, str2, next));
    }
}
