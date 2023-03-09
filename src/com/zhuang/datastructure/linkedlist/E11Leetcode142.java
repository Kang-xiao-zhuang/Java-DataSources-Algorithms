package com.zhuang.datastructure.linkedlist;

/**
 * description: E11Leetcode142
 * date: 2023/3/9 22:41
 * author: Zhuang
 * version: 1.0
 */

/**
 * 检测环的入口
 */
public class E11Leetcode142 {

    public ListNode detectCycle(ListNode head) {
        ListNode rabbit = head; // 兔
        ListNode tortoise = head; // 龟
        // 兔子不走到终点
        while (rabbit != null && rabbit.next != null) {
            rabbit = rabbit.next.next;
            tortoise = tortoise.next;
            if (rabbit == tortoise) {
                // 进入第二阶段
                // 龟回到起点
                tortoise = head;
                while (true) {
                    if (tortoise == rabbit) {
                        // 第二次相遇
                        return tortoise;
                    }
                    tortoise = tortoise.next;
                    rabbit = rabbit.next;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // 构造一个带环链表
        ListNode n12 = new ListNode(12, null);
        ListNode n11 = new ListNode(11, n12);
        ListNode n10 = new ListNode(10, n11);
        ListNode n9 = new ListNode(9, n10);
        ListNode n8 = new ListNode(8, n9);
        ListNode n7 = new ListNode(7, n8);
        ListNode n6 = new ListNode(6, n7);
        ListNode n5 = new ListNode(5, n6);
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);

        n12.next = n1;

        System.out.println(new E11Leetcode142().detectCycle(n1).val);
    }
}
