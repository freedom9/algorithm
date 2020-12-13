package com.freedom.kmp;

/**
 * @author freedom
 * @date 2020/11/29 20:50
 */
public class ViolentMatch {

    public static int violentMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        // i索引指向是s1
        int i = 0;
        // j索引指向是s2
        int j = 0;

        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        if (j == s2Len) {
            return i - j;
        }
        return -1;
    }
}
