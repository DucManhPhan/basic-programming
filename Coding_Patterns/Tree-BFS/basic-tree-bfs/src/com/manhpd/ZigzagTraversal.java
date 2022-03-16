package com.manhpd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.educative.io/courses/grokking-the-coding-interview/qVA27MMYYn0
 *
 * Given a binary tree, populate an array to represent its zigzag level order traversal.
 * You should populate the values of all nodes of the first level from left to right,
 * then right to left for the next level and keep alternating in the same manner for the following levels.
 *
 * Example 1:
 * The original order:
 * [[1],
 * [2; 3],
 * [4, 5; 6, 7]]
 *
 * Zigzag Level Order Traversal:
 *  [[1],
 *  [3, 2],
 *  [4, 5, 6, 7]]
 *
 */
public class ZigzagTraversal {

    public static void main(String[] args) {
        TreeNode root = buildTreeNodeForExample2();
//        TreeNode root = buildTreeNodeForExample1();

        List<List<Integer>> result = ZigzagTraversal.traverse(root);
        System.out.println("Zigzag traversal: " + result);
    }

    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean isZigzag = true;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new LinkedList<>();

            for (int i = 0; i < levelSize; ++i) {
                TreeNode currentNode = queue.poll();

                if (isZigzag) {
                    level.add(0, currentNode.val);
                } else {
                    level.add(currentNode.val);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
            }

            isZigzag = !isZigzag;
            result.add(level);
        }

        return result;
    }

    private static TreeNode buildTreeNodeForExample1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    private static TreeNode buildTreeNodeForExample2() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(9);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        root.right.left.left = new TreeNode(20);
        root.right.left.right = new TreeNode(17);

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
