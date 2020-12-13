package com.freedom.binarysearchnonrecursive;

/**
 * @author freedomve
 * @date 2020/11/26 20:31
 */
public class BinarySearchNonRecursiveTest {
//Non-recursive
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        System.out.println("index = " + BinarySearchNonRecursive.binarySearch(arr, 100));
    }
}
