package com.zhuang.datastructure.binarytree;

public class TestTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(null, 2, new TreeNode(4)),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );

        preOrder(root);
        System.out.println();

        inOrder(root);
        System.out.println();

        postOrder(root);
        System.out.println();
    }

    // 前序遍历
    public static void preOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node + "\t");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序遍历
    public static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node + "\t");
        inOrder(node.right);
    }

    // 后序遍历
    public static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node + "\t");
    }
}
