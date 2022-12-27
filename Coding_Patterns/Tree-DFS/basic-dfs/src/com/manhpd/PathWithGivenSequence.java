package com.manhpd;

/**
 * Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the given tree.
 *
 * Example 1:
 * Tree: [1], [7, 9], [[], [2, 9]]
 * Sequence: [1, 9, 9]
 * Output: true
 * Explanation: The tree has a path 1 -> 9 -> 9.
 *
 * Example 2:
 * Tree: [1], [0, 1], [[1], [6, 5]]
 *
 * Sequence: [1, 0, 7]
 * Output: false
 * Explanation: The tree does not have a path 1 -> 0 -> 7.
 *
 * Sequence: [1, 1, 6]
 * Output: true
 * Explanation: The tree has a path 1 -> 1 -> 6.
 *
 */
public class PathWithGivenSequence {

    public static boolean findPath(TreeNode root, int[] sequence) {
        return findPathBasedOnSequencer(root, sequence, 0);
    }

    private static boolean findPathBasedOnSequencer(TreeNode root, int[] sequence, int idx) {
        if (root == null || idx >= sequence.length) {
            return false;
        }

        if (root.val != sequence[idx]) {
            return false;
        } else {
            if (root.left == null && root.right == null) {
                return true;
            }
        }

        return findPathBasedOnSequencer(root.left, sequence, idx + 1)
            || findPathBasedOnSequencer(root.right, sequence, idx + 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(5);

        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 0, 7 }));
        System.out.println("Tree has path sequence: " + PathWithGivenSequence.findPath(root, new int[] { 1, 1, 6 }));
    }

}
