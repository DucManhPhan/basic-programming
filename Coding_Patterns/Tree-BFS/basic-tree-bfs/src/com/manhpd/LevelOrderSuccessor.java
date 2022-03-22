package com.manhpd;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/7nO4VmA74Lr
 *
 * Given a binary tree and a node, find the level order successor of the given node in the tree.
 * The level order successor is the node that appears right after the given node in the level order traversal.
 *
 * Example 1:
 * [[1],
 * [2; 3],
 * [4, 5]
 * ]
 * Given node: 3
 * Level Order Successor: 4
 *
 * Example 2:
 * [[12],
 * [7; 1],
 * [9 ; 10, 5]
 * ]
 * Given node: 9
 * Level Order Successor: 10
 *
 * Example 3:
 * [[12],
 * [7; 1],
 * [9 ; 10, 5]
 * ]
 * Given node: 12
 * Level Order Successor: 7
 *
 *
 */
public class LevelOrderSuccessor {

    public static void main(String[] args) {
        TreeNode root = build2ndExample();
        TreeNode result = LevelOrderSuccessor.findSuccessor(root, 12);

        if (result != null) {
            System.out.println(result.val + " ");
        }

        result = LevelOrderSuccessor.findSuccessor(root, 9);
        if (result != null) {
            System.out.println(result.val + " ");
        }
    }

    public static TreeNode findSuccessor(TreeNode root, int key) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean isFoundGivenNode = false;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; ++i) {
                TreeNode currentNode = queue.poll();

                if (isFoundGivenNode) {
                    return currentNode;
                }

                if (currentNode.val == key) {
                    isFoundGivenNode = true;
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }

        return null;
    }

    public static TreeNode build1stExample() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

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

        TreeNode(int x) {
            val = x;
        }
    };

}
