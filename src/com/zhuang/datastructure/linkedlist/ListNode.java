package com.zhuang.datastructure.linkedlist;

/**
 * description: ListNode
 * date: 2023/3/5 12:32
 * author: Zhuang
 * version: 1.0
 */
public class ListNode {

    int val;

    ListNode next;


    ListNode() {
    }


    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder(64);
//        sb.append("[");
//        ListNode p = this;
//        while (p != null) {
//            sb.append(p.val);
//            if (p.next != null) {
//                sb.append(",");
//            }
//            p = p.next;
//        }
//        sb.append("]");
//        return sb.toString();
        return String.valueOf(this.val);
    }

    public static ListNode of(int... elements) {
        if (elements.length == 0) {
            return null;
        }
        ListNode p = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            p = new ListNode(elements[i], p);
        }
        return p;
    }

    public ListNode append(ListNode last) {
        ListNode p = this;
        while (p.next != null) {
            p = p.next;
        }
        p.next = last;
        return this;
    }
}