package com.zhuang.algorithms.recursion_single;

/**
 * description: E06Sum
 * date: 2023/3/3 15:10
 * author: Zhuang
 * version: 1.0
 */
public class E06Sum {
    public static long sum(long n) {
        if (n == 1) {
            return 1;
        }
        return n + sum(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(sum(10));
    }
}
