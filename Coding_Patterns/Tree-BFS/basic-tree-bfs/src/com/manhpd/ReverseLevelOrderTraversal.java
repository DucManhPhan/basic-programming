package com.manhpd;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/m2N6GwARL8r
 *
 * Given a binary tree, populate an array to represent its level-by-level traversal in reverse order, i.e., the lowest level comes first.
 * You should populate the values of all nodes in each level from left to right in separate sub-arrays.
 *
 * Example 1:
 * [[4,5,6,7],
 * [2,3],
 * [1]]
 *
 * Example 2:
 * [[9, 10, 5],
 * [7, 1],
 * [12]]
 *
 */
public class ReverseLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = ReverseLevelOrderTraversal.buildTreeNodeForExample1();
        List<List<Integer>> result = ReverseLevelOrderTraversal.traverse(root);
        System.out.println("Reverse level order traversal: " + result);
    }

    public static List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levels = new ArrayList<>(levelSize);

            for (int i = 0; i < levelSize; ++i) {
                TreeNode currentNode = queue.poll();
                levels.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            result.add(0, levels);
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

