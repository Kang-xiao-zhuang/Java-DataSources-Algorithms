package com.zhuang.datastructure.linkedlist;

/**
 * description: E08Leetcode876
 * date: 2023/3/8 20:16
 * author: Zhuang
 * version: 1.0
 */

/**
 * 查找链表中间节点
 */
public class E08Leetcode876 {
    /*
                p1
                        p2
        1   2   3   4   5   null


                    p1
                                p2
        1   2   3   4   5   6   null
     */

    /**
     * @param head 头节点
     * @return 中间节点
     */
    public ListNode middleNode(ListNode head) {
        // 快慢指针方法
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        ListNode head1 = ListNode.of(1, 2, 3, 4, 5);
        System.out.println(new E08Leetcode876().middleNode(head1));

        ListNode head2 = ListNode.of(1, 2, 3, 4, 5, 6);
        System.out.println(new E08Leetcode876().middleNode(head2));
    }
}