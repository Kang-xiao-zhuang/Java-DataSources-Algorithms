package com.zhuang.datastructure.linkedlist;

/**
 * description: E09Leetcode234
 * date: 2023/3/8 20:21
 * author: Zhuang
 * version: 1.0
 */

/**
 * 判断回文链表
 */
public class E09Leetcode234 {
    /*
    步骤1. 找中间点的同时反转前半个链表
    步骤2. 反转后的前半个链表与中间点开始的后半个链表 逐一比较
            p1      p2
    1   2   2   1   null

    n1
    2   1

 */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 新链表的头
        ListNode n1 = null;
        // 旧链表的头
        ListNode o1 = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 反转链表
            //       ListNode o2 = o1.next;
            o1.next = n1;
            n1 = o1;
            //        o1 = o2;
            o1 = slow;
        }
        if (fast != null) { // 奇数节点
            slow = slow.next;
        }

        while (n1 != null) {
            if (n1.val != slow.val) {
                return false;
            }
            n1 = n1.next;
            slow = slow.next;
        }
        return true;
    }


    public static void main(String[] args) {
//        System.out.println(new E09Leetcode234()
//                .isPalindrome(ListNode.of(1, 2, 2, 1)));
        System.out.println(new E09Leetcode234()
                .isPalindrome(ListNode.of(1, 2, 3, 2, 1)));
    }

    /*
                p1
                        p2
        1   2   3   2   1   null

        n1
        2   1
     */

}