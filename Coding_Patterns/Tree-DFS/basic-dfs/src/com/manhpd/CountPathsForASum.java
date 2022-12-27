package com.manhpd;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

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
        System.out.println("Tree has path: " + CountPathsForASum.countPathsV2(root, S));
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
