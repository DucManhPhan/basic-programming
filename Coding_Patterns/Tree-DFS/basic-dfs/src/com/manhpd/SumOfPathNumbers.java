package com.manhpd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a binary tree where each node can only have a digit (0-9) value, each root-to-leaf path will represent a number.
 * Find the total sum of all the numbers represented by all paths.
 *
 * Example 1:
 * Tree: [1], [7, 9], [[], [2, 9]]
 * Output: 408
 * Explanation: The sum of all path numbers: 17 + 192 + 199
 *
 * Example 2:
 * Tree: [1], [0, 1], [[1], [6, 5]]
 * Output: 332
 * Explanation: The sum of all path numbers: 101 + 116 + 115
 *
 */
public class SumOfPathNumbers {

    public static int findSumOfPathNumbers(TreeNode root) {
        // TODO: Write your code here
        List<Integer> pathNumbers = new ArrayList<>();
        getNumbers(root, pathNumbers, new ArrayList<>());

        if (!pathNumbers.isEmpty()) {
            return pathNumbers.stream()
                    .reduce(0, Integer::sum);
        }

        return -1;
    }

    public static int findSumOfPathNumbersV2(TreeNode root) {
        return findRootToLeafPathNumbers(root, 0);
    }

    private static int findRootToLeafPathNumbers(TreeNode currentNode, int pathSum) {
        if (currentNode == null) {
            return 0;
        }

        pathSum = 10 * pathSum + currentNode.val;

        if (currentNode.left == null && currentNode.right == null) {
            return pathSum;
        }

        return findRootToLeafPathNumbers(currentNode.left, pathSum) + findRootToLeafPathNumbers(currentNode.right, pathSum);
    }

    private static void getNumbers(TreeNode root, List<Integer> pathNumbers, List<Integer> currentPath) {
        if (root == null) {
            return;
        }

        int value = root.val;
        currentPath.add(value);

        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            currentPath.forEach(num -> sb.append(num));
            System.out.println("num: " + sb);

            pathNumbers.add(Integer.valueOf(sb.toString()));
        } else {
            getNumbers(root.left, pathNumbers, currentPath);
            getNumbers(root.right, pathNumbers, currentPath);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

//        System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbers(root));
        System.out.println("Total Sum of Path Numbers: " + SumOfPathNumbers.findSumOfPathNumbersV2(root));
    }

}
