package com.zhuang.algorithms.recursion_single;

import java.util.Arrays;

/**
 * description: E05InsertionSort
 * date: 2023/3/3 15:06
 * author: Zhuang
 * version: 1.0
 */
public class E05InsertionSort {

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] a) {
        insertion2(a, 1);
    }

    /**
     * <h3>递归函数 将 low 位置的元素插入至 [0..low-1] 的已排序区域</h3>
     *
     * @param a   数组
     * @param low 未排序区域的左边界
     */
    private static void insertion(int[] a, int low) {
        if (low == a.length) {
            return;
        }

        int t = a[low];
        int i = low - 1; // 已排序区域指针

        while (i >= 0 && a[i] > t) { // 没有找到插入位置
            a[i + 1] = a[i]; // 空出插入位置
            i--;
        }

        // 找到插入位置
        if (i + 1 != low) {
            a[i + 1] = t;
        }

        insertion(a, low + 1);
    }

    private static void insertion2(int[] a, int low) {
        if (low == a.length) {
            return;
        }

        int i = low - 1;
        while (i >= 0 && a[i] > a[i + 1]) {
            int t = a[i];
            a[i] = a[i + 1];
            a[i + 1] = t;

            i--;
        }

        insertion(a, low + 1);
    }
}
