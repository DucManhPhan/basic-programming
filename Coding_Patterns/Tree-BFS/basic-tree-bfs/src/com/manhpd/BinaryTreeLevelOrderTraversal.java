package com.manhpd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, populate an array to represent its level-by-level traversal.
 * You should populate the values of all nodes of each level from left to right in separate sub-arrays.
 *
 * Example 1:
 * Level Order Traversal:
 *  [[1],
 *  [2,3],
 *  [4,5;6,7]]
 *
 *  Example 2:
 *  Level Order Traversal:
 *  [[12],
 *  [7,1],
 *  [9;10,5]]
 *
 */
public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
//        TreeNode root = BinaryTreeLevelOrderTraversal.buildTreeNodeForExample2();
        TreeNode root = BinaryTreeLevelOrderTraversal.buildTreeNodeForExample1();

        List<List<Integer>> result = BinaryTreeLevelOrderTraversal.traverseV2(root);
        System.out.println("Level order traversal: " + result);
    }

    /**
     * Using null value to separate between levels
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> traverse(TreeNode root) {
        List<Integer> level = new ArrayList<>();
        level.add(root.val);

        List<List<Integer>> result = new ArrayList<>();
        result.add(level);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null); // add null to separate between levels in binary tree

        level = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();

            if (tmp != null) {
                if (tmp.left != null) {
                    queue.add(tmp.left);
                    level.add(tmp.left.val);
                }

                if (tmp.right != null) {
                    queue.add(tmp.right);
                    level.add(tmp.right.val);
                }
            } else {
                if (!level.isEmpty()) {
                    result.add(level);
                    level = new ArrayList<>();

                    queue.add(null);
                }
            }
        }

        return result;
    }

    /**
     * Using level size variable to determine how many nodes in each level.
     *
     * This way is concise, clear.
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> traverseV2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);

            for (int i = 0; i < levelSize; ++i) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            result.add(currentLevel);
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

