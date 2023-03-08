package com.zhuang.datastructure.linkedlist;

/**
 * 根据值删除节点
 */
public class E03Leetcode19 {

    /**
     * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
     *
     * @param head 链表头
     * @param n    目标值
     * @return 删除后的链表头
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode sential = new ListNode(-1, head);
        recursion(sential, n);
        return sential.next;
    }

    public int recursion(ListNode p, int n) {
        if (p == null) {
            return 0;
        }
        // 下一个节点的倒数位置
        int nth = recursion(p.next, n);
        if (nth == n) {
            p.next = p.next.next;
        }
        return nth + 1;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode s = new ListNode(-1, head);
        ListNode p1 = s;
        ListNode p2 = s;
        for (int i = 0; i < n + 1; i++) {
            p2 = p2.next;
        }
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return s.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 6, 3, 6);
//        ListNode head = ListNode.of(7, 7, 7, 7);
        System.out.println(head);
    }
}
