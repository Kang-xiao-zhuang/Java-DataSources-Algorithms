package com.zhuang.datastructure.linkedlist;

/**
 * 有序链表去重
 */
public class E04Leetcode83 {

    public ListNode deleteDuplicates1(ListNode head) {
        // 节点数小于2
        if (head == null || head.next == null) {
            return head;
        }
        // 节点数大于等于2
        ListNode p1 = head;
        ListNode p2;
        while ((p2 = p1.next) != null) {
            if (p1.val == p2.val) {
                // 删除p2
                p1.next = p2.next;
            } else {
                // 向后平移
                p1 = p1.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            return deleteDuplicates2(head.next);
        } else {
            head.next = deleteDuplicates2(head.next);
            return head;
        }
    }



    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 6, 3, 6);
//        ListNode head = ListNode.of(7, 7, 7, 7);
        System.out.println(head);
    }
}
