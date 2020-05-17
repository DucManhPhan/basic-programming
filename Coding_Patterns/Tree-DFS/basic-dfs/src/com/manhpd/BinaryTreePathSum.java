package com.manhpd;

/**
 * Given a binary tree and a number ‘S’,
 * find if the tree has a path from root-to-leaf such that the sum of all the node values of that path equals ‘S’.
 *
 * Example 1: Input: LNR = 4 2 5 1 6 3 7; S = 1;
 *            Output: true
 *
 * Example 2: Input: LNr = 9 7 12 10 1 5; S = 23 or S = 16
 *            Output: true or false
 *
 */
public class BinaryTreePathSum {

    public static boolean hasPath(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        sum -= root.val;
        if (sum == 0 && (root.left == null && root.right == null)) {
            return true;
        }

        boolean isInLeftSide = hasPath(root.left, sum);
        boolean isInRightSide = hasPath(root.right, sum);

        return isInLeftSide || isInRightSide;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(9);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        System.out.println("Tree has path: " + BinaryTreePathSum.hasPath(root, 23));
        System.out.println("Tree has path: " + BinaryTreePathSum.hasPath(root, 16));
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }

    TreeNode(int x, TreeNode left, TreeNode right) {
        this.val = x;
        this.left = left;
        this.right = right;
    }
};
