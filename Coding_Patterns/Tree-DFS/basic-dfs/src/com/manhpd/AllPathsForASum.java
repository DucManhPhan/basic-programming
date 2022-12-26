package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a number ‘S’, find all paths from root-to-leaf such that the sum of all the node values of each path equals ‘S’.
 *
 * Example 1:
 * S: 12
 * Output: [[1, 7, 4], [1, 9, 2]]
 * Explanation:
 *      Here are the two paths with sum '12':1 -> 7 -> 4 and 1 -> 9 -> 2
 *
 * Example 2:
 * S: 23
 * Output: [[12, 7, 4], [12, 1, 10]]
 * Explanation:
 *      Here are the two paths with sum '23':12 -> 7 -> 4 and 12 -> 1 -> 10
 */
public class AllPathsForASum {

    public static List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        // TODO: Write your code here
        recursivePaths(root, sum, allPaths, new ArrayList<>());

        return allPaths;
    }

    private static boolean recursivePaths(TreeNode root, int sum, List<List<Integer>> allPaths, List<Integer> currentPath) {
        if (root == null) {
            return false;
        }

        int value = root.val;
        currentPath.add(value);

        int currentSum = sum - value;
        if (currentSum == 0 && (root.left == null && root.right == null)) {
            allPaths.add(new ArrayList<>(currentPath));
            currentPath.remove(currentPath.size() - 1);

            return true;
        }

        boolean isLeftPath = recursivePaths(root.left, currentSum, allPaths, currentPath);
        boolean isRightPath = recursivePaths(root.right, currentSum, allPaths, currentPath);

        currentPath.remove(currentPath.size() - 1);
        return isLeftPath || isRightPath;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        int sum = 23;
        List<List<Integer>> result = AllPathsForASum.findPaths(root, sum);
        System.out.println("Tree paths with sum " + sum + ": " + result);
    }

}
