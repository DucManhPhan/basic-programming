package com.manhpd;

import java.util.*;

/**
 * Given a binary tree and a number ‘S’, find all paths in the tree such that the sum of all the node values of each path equals ‘S’.
 * Please note that the paths can start or end at any node but all paths must follow direction from parent to child (top to bottom).
 *
 * Example 1:
 * Tree: [1], [7, 9], [[6, 5], [2, 3]]
 * S: 12
 * Output: 3
 * Explanation: There are three paths with sum '12':7 -> 5, 1 -> 9 -> 2, and 9 -> 3
 *
 * Example 2:
 * Tree: [12], [7, 1], [[4], [10, 5]]
 * S: 11
 * Output: 2
 * Explanation: Here are the two paths with sum '11':7 -> 4 . and 1 -> 10.
 *
 */
public class CountPathsForASum {

    public static int countPathsV1(TreeNode root, int S) {
        return count(root, S, 0);
    }

    public static int countPathsV2(TreeNode root, int S) {
        return countPathsRecursive(root, S, new LinkedList<>());
    }

    public static int countPathsV3(TreeNode root, int S) {
        // A map that stores the number of times a prefix sum has occurred so far.
        return countPathsPrefixSum(root, S, new HashMap<>(), 0);
    }

    public static int countPathsPrefixSum(TreeNode node, int targetSum, Map<Integer, Integer> map, Integer currentPathSum) {
        if (node == null)
            return 0;

        // The number of paths that have the required sum.
        int pathCount = 0;

        // 'currentPathSum' is the prefix sum, i.e., sum of all node values from root to current node.
        currentPathSum += node.val;

        // This is the base case. If the current sum is equal to the target sum, we have found a path from root to
        // the current node having the required sum. Hence, we increment the path count by 1.
        if (targetSum == currentPathSum)
            pathCount++;

        //'currentPathSum' is the path sum from root to the current node. If within this path, there is a
        // valid solution, then there must be an 'oldPathSum' such that:
        // => currentPathSum - oldPathSum = targetSum
        // => currentPathSum - targetSum = oldPathSum
        // Hence, we can search such an 'oldPathSum' in the map from the key 'currentPathSum - targetSum'.
        pathCount += map.getOrDefault(currentPathSum - targetSum, 0);

        // This is the key step in the algorithm. We are storing the number of times the prefix sum
        // `currentPathSum` has occurred so far.
        map.put(currentPathSum, map.getOrDefault(currentPathSum, 0) + 1);

        // Counting the number of paths from the left and right subtrees.
        pathCount += countPathsPrefixSum(node.left, targetSum, map, currentPathSum);
        pathCount += countPathsPrefixSum(node.right, targetSum, map, currentPathSum);

        // Removing the current path sum from the map for backtracking.
        // 'currentPathSum' is the prefix sum up to the current node. When we go back (i.e., backtrack), then
        // the current node is no more a part of the path, hence, we should remove its prefix sum from the map.
        map.put(currentPathSum, map.getOrDefault(currentPathSum, 1) - 1);

        return pathCount;
    }

    private static int countPathsRecursive(TreeNode root, int S, List<Integer> currentPath) {
        if (root == null) {
            return 0;
        }

        currentPath.add(root.val);

        int pathSum = 0;
        int pathCount = 0;

        ListIterator<Integer> pathIterator = currentPath.listIterator(currentPath.size());
        while (pathIterator.hasPrevious()) {
            pathSum += pathIterator.previous();
            if (pathSum == S) {
                ++pathCount;
            }
        }

        pathCount += countPathsRecursive(root.left, S, currentPath);
        pathCount += countPathsRecursive(root.right, S, currentPath);

        currentPath.remove(currentPath.size() - 1);
        return pathCount;
    }

    private static int count(TreeNode root, int S, int currentSum) {
        if (root == null) {
            return 0;
        }

        int counter = 0;
        if (currentSum + root.val == S) {
            ++counter;
        }

        return counter + count(root.left, S, currentSum)
             + count(root.left, S, currentSum + root.val)
             + count(root.right, S, currentSum)
             + count(root.right, S, currentSum + root.val);
    }

    public static void main(String[] args) {
        TreeNode root = buildExample1();
        int S = 12;
//
//        TreeNode root = buildExample2();
//        int S = 11;

//        System.out.println("Tree has path: " + CountPathsForASum.countPathsV1(root, S));
//        System.out.println("Tree has path: " + CountPathsForASum.countPathsV2(root, S));
        System.out.println("Tree has path: " + CountPathsForASum.countPathsV3(root, S));
    }

    public static TreeNode buildExample2() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        return root;
    }

    public static TreeNode buildExample1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(9);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(3);

        return root;
    }

}
