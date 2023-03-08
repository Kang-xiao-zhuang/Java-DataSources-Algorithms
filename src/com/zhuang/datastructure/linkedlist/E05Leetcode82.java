package com.zhuang.datastructure.linkedlist;

/**
 * description: E05Leetcode82
 * date: 2023/3/8 15:17
 * author: Zhuang
 * version: 1.0
 */

/**
 * 有序链表去重
 */
public class E05Leetcode82 {

    /**
     * https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/
     *
     * @param head ListNode
     * @return ListNode
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            ListNode x = head.next.next;
            while (x != null && x.val == head.val) {
                x = x.next;
            }
            // x 就是与 p 取值不同的节点
            return deleteDuplicates1(x);
        } else {
            head.next = deleteDuplicates1(head.next);
            return head;
        }
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2, p3;
        while ((p2 = p1.next) != null && (p3 = p2.next) != null) {
            if (p2.val == p3.val) {
                while ((p3 = p3.next) != null && p3.val == p2.val) {

                }
                // 找到不重复的值
                p1.next = p3;
            } else {
                p1 = p1.next;
            }
        }
        return s.next;
    }
}
