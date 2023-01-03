package com.manhpd;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, write a program to find the maximum depth of the binary tree.
 *
 * The maximum depth is the number of nodes along the longest path from the root node to the leaf node.
 * A leaf is a node with no child nodes.
 *
 * The height of a node is the number of edges present in the longest path connecting that node to a leaf node.
 *
 * Example 1:
 * Input: Given binary tree,
 * [5, 3, 6, 2, 4, -1, -1, 1, -1, -1, -1, -1, -1]
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 * Output: return its depth = 4
 *
 */
public class HeightOfBinaryTree {

    /**
     * Recursive way: Using DFS algorithm
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param root
     * @return
     */
    public static int heightOfBinaryTree(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int heightOfLeftSubTree = heightOfBinaryTree(root.left);
        int heightOfRightSubTree = heightOfBinaryTree(root.right);

        return Math.max(heightOfLeftSubTree, heightOfRightSubTree) + 1;
    }

    /**
     * Iterative way: Using BFS algorithm
     *
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param root
     * @return
     */
    public static int heightOfBinaryTreeV2(TreeNode root) {
        if (root == null) {
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int height = -1;
        while (true) {
            int levelNodes = queue.size();
            if (levelNodes == 0) {
                return height;
            }

            height++;

            while (levelNodes > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                --levelNodes;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = buildExample2();

        System.out.println("Height of a binary tree: ");
        System.out.println("DFS way: " + heightOfBinaryTree(root));
        System.out.println("BFS way: " + heightOfBinaryTreeV2(root));
    }

    private static TreeNode buildExample1() {
        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(-7);
        root.right= new TreeNode(-3);

        root.right.left = new TreeNode(-9);
        root.right.right = new TreeNode(-3);

        root.right.left.left = new TreeNode(9);
        root.right.left.right = new TreeNode(-7);
        root.right.right.left = new TreeNode(-4);

        root.right.left.left.left = new TreeNode(6);
        root.right.left.left.left.left = new TreeNode(0);
        root.right.left.left.left.left.left = new TreeNode(-1);
        root.right.left.left.left.right = new TreeNode(6);
        root.right.left.left.left.right.left = new TreeNode(-4);

        root.right.left.right.left = new TreeNode(-6);
        root.right.left.right.left.left = new TreeNode(5);
        root.right.left.right.right = new TreeNode(-6);
        root.right.left.right.right.left = new TreeNode(9);
        root.right.left.right.right.left.left = new TreeNode(-2);

        return root;
    }

    private static TreeNode buildExample2() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        return root;
    }

}
