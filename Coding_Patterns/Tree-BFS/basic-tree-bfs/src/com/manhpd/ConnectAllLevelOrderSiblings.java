package com.manhpd;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/NE5109Jl02v
 * <p>
 * Given a binary tree, connect each node with its level order successor.
 * The last node of each level should point to the first node of the next level.
 * <p>
 * Example 1:
 * [[1],
 * [2; 3],
 * [4, 5; 6, 7]
 * ]
 * <p>
 * Example 2:
 * [[12],
 * [7; 1],
 * [9; 10, 5]
 * ]
 */
public class ConnectAllLevelOrderSiblings {

    public static void main(String[] args) {
//        TreeNode root = build1stExample();
        TreeNode root = build2ndExample();
        connect(root);

        // level order traversal using 'next' pointer
        TreeNode current = root;
        System.out.println("Traversal using 'next' pointer: ");
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
    }

    public static void connect(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode previousNode = null;
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            if (previousNode != null) {
                previousNode.next = currentNode;
            }

            previousNode = currentNode;

            if (currentNode.left != null) {
                queue.offer(currentNode.left);
            }

            if (currentNode.right != null) {
                queue.offer(currentNode.right);
            }
        }
    }

    public static TreeNode build1stExample() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    public static TreeNode build2ndExample() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(9);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int x) {
            val = x;
            left = right = next = null;
        }
    }

    ;

}