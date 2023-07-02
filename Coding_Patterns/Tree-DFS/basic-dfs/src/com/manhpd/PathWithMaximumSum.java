package com.manhpd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    private static int globalMaximumSum = Integer.MIN_VALUE;

    /**
     * 1st way: Using Diameter concept
     *
     * @param root
     * @return
     */
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

    /**
     * 2nd way: Using the same concept of Diameter.
     *
     * @param root
     * @return
     */
    public static int findMaximumPathSumV2(TreeNode root) {
        globalMaximumSum = Integer.MIN_VALUE;
        findMaximumPathSumRecursive(root);
        return globalMaximumSum;
    }

    private static int findMaximumPathSumRecursive(TreeNode currentNode) {
        if (currentNode == null)
            return 0;

        int maxPathSumFromLeft = findMaximumPathSumRecursive(currentNode.left);
        int maxPathSumFromRight = findMaximumPathSumRecursive(currentNode.right);

        // ignore paths with negative sums, since we need to find the maximum sum we should
        // ignore any path which has an overall negative sum.
        maxPathSumFromLeft = Math.max(maxPathSumFromLeft, 0);
        maxPathSumFromRight = Math.max(maxPathSumFromRight, 0);

        // maximum path sum at the current node will be equal to the sum from the left subtree +
        // the sum from right subtree + val of current node
        int localMaximumSum = maxPathSumFromLeft + maxPathSumFromRight + currentNode.val;

        // update the global maximum sum
        globalMaximumSum = Math.max(globalMaximumSum, localMaximumSum);

        // maximum sum of any path from the current node will be equal to the maximum of
        // the sums from left or right subtrees plus the value of the current node
        return Math.max(maxPathSumFromLeft, maxPathSumFromRight) + currentNode.val;
    }

    public static void main(String[] args) {
        TreeNode root = buildExample1();
        System.out.println("Maximum Path Sum V1: " + PathWithMaximumSum.findMaximumPathSum(root));
        System.out.println("Maximum Path Sum V2: " + PathWithMaximumSum.findMaximumPathSumV2(root));

        TreeNode root1 = buildExample2();
        System.out.println("Maximum Path Sum: " + PathWithMaximumSum.findMaximumPathSum(root1));
        System.out.println("Maximum Path Sum V2: " + PathWithMaximumSum.findMaximumPathSumV2(root1));

        TreeNode root2 = buildExample3();
        System.out.println("Maximum Path Sum: " + PathWithMaximumSum.findMaximumPathSum(root2));
        System.out.println("Maximum Path Sum V2: " + PathWithMaximumSum.findMaximumPathSumV2(root2));
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
