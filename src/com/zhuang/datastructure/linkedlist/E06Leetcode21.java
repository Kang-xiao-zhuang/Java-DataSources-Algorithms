package com.zhuang.datastructure.linkedlist;

/**
 * description: E06Leetcode21
 * date: 2023/3/8 19:45
 * author: Zhuang
 * version: 1.0
 */
public class E06Leetcode21 {

    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode s = new ListNode(-1, null);
        ListNode p = s;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        // 全部连上去
        if (list1 != null) {
            p.next = list1;
        }
        // 全部连上去
        if (list2 != null) {
            p.next = list2;
        }
        return s.next;
    }

    public ListNode mergeTwoLists2(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        }
        if (p2 == null) {
            return p1;
        }
        // 返回更小的链表节点，并把剩余节点与另一个链表再次递归
        // 返回之前，更新此节点的next
        if (p1.val < p2.val) {
            p1.next = mergeTwoLists2(p1.next, p2);
            return p1;
        } else {
            p2.next = mergeTwoLists2(p1, p2.next);
            return p2;
        }
    }


    public static void main(String[] args) {
        ListNode p1 = ListNode.of(1, 3, 8, 9);
        ListNode p2 = ListNode.of(2, 4);
        System.out.println(new E06Leetcode21()
                .mergeTwoLists1(p1, p2));
    }
}
