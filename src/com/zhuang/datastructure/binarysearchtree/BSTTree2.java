package com.zhuang.datastructure.binarysearchtree;

/**
 * 二叉搜索树, 泛型 key 版本
 */
public class BSTTree2<K extends Comparable<K>, V> {

    static class BSTNode<K, V> {
        K key;
        V value;
        BSTNode<K, V> left;
        BSTNode<K, V> right;

        public BSTNode(K key) {
            this.key = key;
        }

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    BSTNode<K, V> root;

    public V get(K key) {
        BSTNode<K, V> p = root;
        while (p != null) {
            /*
                -1 key < p.key
                0 key == p.key
                1 key > p.key
             */
            int cmp = key.compareTo(p.key);
            // 小于
            if (cmp < 0) {
                p = p.left;
                // 大于
            } else if (cmp > 0) {
                p = p.right;
            } else {
                // 等于
                return p.value;
            }
        }
        return null;
    }
}
