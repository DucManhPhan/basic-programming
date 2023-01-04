package com.manhpd;

/**
 * Find the path with the maximum sum in a given binary tree. Write a function that returns the maximum sum.
 * A path can be defined as a sequence of nodes between any two nodes and doesn’t necessarily pass through the root.
 * The path must contain at least one node.
 *
 * Example 1:
 *  Tree: [1, 2, 3, 4, null, 5, 6]
 *  Output: 16
 *  Explanation: The path with maximum sum is: [4, 2, 1, 3, 6]
 *
 * Example 2:
 *  Tree: [1, 2, 3, 1, 3, 5, 6, null, null, null, null, 7, 8, 9, null]
 *  Output: 31
 *  Explanation: The path with maximum sum is: [8, 5, 3, 6, 9]
 *
 */
public class PathWithMaximumSum {

    private static int maxPathSum = Integer.MIN_VALUE;

    private static int maxDiameter = Integer.MIN_VALUE;

    public static int findMaximumPathSum(TreeNode root) {
        findHeight(root, 0);
        return maxPathSum;
    }

    private static int findHeight(TreeNode root, int currentPathSum) {
        if (root == null) {
            return -1;
        }

        currentPathSum += root.val;

        int leftHeight = findHeight(root.left, currentPathSum);
        int rightHeight = findHeight(root.right, currentPathSum);

        if (maxDiameter < leftHeight + rightHeight + 2) {
            maxDiameter = leftHeight + rightHeight + 2;
            maxPathSum = Math.max(currentPathSum, maxPathSum);
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = buildExample1();
        System.out.println("Maximum Path Sum: " + PathWithMaximumSum.findMaximumPathSum(root));

//        TreeNode root = buildExample2();
//        System.out.println("Maximum Path Sum: " + PathWithMaximumSum.findMaximumPathSum(root));

//        Tree Node root = buildExample3();
//        System.out.println("Maximum Path Sum: " + PathWithMaximumSum.findMaximumPathSum(root));
    }

    private static TreeNode buildExample1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        return root;
    }

    private static TreeNode buildExample2() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        return root;
    }

    private static TreeNode buildExample3() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right.left = new TreeNode(9);

        return root;
    }

}
