package com.manhpd;

import javafx.util.Pair;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/3jwVx84OMkO
 *
 * Find the minimum depth of a binary tree.
 * The minimum depth is the number of nodes along the shortest path from the root node to the nearest leaf node.
 *
 * Example 1:
 * [[1],
 * [2; 3],
 * [4, 5]
 * ]
 * Minimum Depth: 2
 *
 * Example 2:
 * [[12],
 * [7; 1],
 * [; 10, 5]
 * ]
 * Minimum Depth: 2
 *
 * Example 3:
 * [[12],
 * [7; 1],
 * [9; 10, 5]
 * [; 11; ]
 * ]
 * Minimum Depth: 3
 */
public class MinDepthBinaryTree {

    public static void main(String[] args) {
        TreeNode root = build1stExample();
        System.out.println("Tree Minimum Depth: " + MinDepthBinaryTree.findDepthV2(root));

//        TreeNode root = build2ndExample();
        TreeNode root1 = build3rdExample();
        System.out.println("Tree Minimum Depth: " + MinDepthBinaryTree.findDepthV2(root));
    }

    public static int findDepthV2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int minDepth = 0;

        while (!queue.isEmpty()) {
            minDepth++;

            int levelSize = queue.size();
            for (int i = 0; i < levelSize; ++i) {
                TreeNode currentNode = queue.poll();

                if (currentNode.left == null && currentNode.right == null) {
                    return minDepth;
                }

                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }

        return minDepth;
    }

    /**
     * With this way, we always keep track of the depth of each node.
     *
     * @param root
     * @return
     */
    public static int findDepth(TreeNode root) {
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 1));

        int minDepth = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> currentPair = queue.poll();
            TreeNode currentNode = currentPair.getKey();
            int currentDepth = currentPair.getValue();

            if (currentNode != root) {
                if (currentNode.left == null && currentNode.right == null) {
                    minDepth = Math.min(minDepth, currentDepth);
                }
            }

            if (currentNode.left != null) {
                queue.offer(new Pair<>(currentNode.left, currentDepth + 1));
            }

            if (currentNode.right != null) {
                queue.offer(new Pair<>(currentNode.right, currentDepth + 1));
            }
        }

        return minDepth;
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

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        return root;
    }

    public static TreeNode build3rdExample() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(9);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        root.right.left.left = new TreeNode(11);

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
