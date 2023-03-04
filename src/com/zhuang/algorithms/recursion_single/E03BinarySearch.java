package com.zhuang.algorithms.recursion_single;

/**
 * description: E03BinarySearch
 * date: 2023/3/2 22:04
 * author: Zhuang
 * version: 1.0
 */
public class E03BinarySearch {

    public static void main(String[] args) {
        int[] a = {7, 13, 21, 30, 38, 44, 62, 65};
        System.out.println(search(a, 21));
    }


    public static int search(int[] a, int target) {
        return f(a, target, 0, a.length - 1);
    }

    private static int f(int[] a, int target, int i, int j) {
        if (i > j) {
            return -1;
        }
        int mid = (i + j) >>> 1;
        if (target < a[mid]) {
            return f(a, target, i, mid - 1);
        } else if (a[mid] < target) {
            return f(a, target, mid + 1, j);
        } else {
            return mid;
        }
    }
}
