package com.manhpd;

/**
 * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
 * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
 * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
 * Return true if and only if the nodes corresponding to the values x and y are cousins.
 *
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 *
 * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 * Output: true
 *
 * Input: root = [1,2,3,null,4], x = 2, y = 3
 * Output: false
 *
 */
public class CousinsOfBinaryTree {

    private static TreeNode parentNode;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);

        int x = 4;
        int y = 5;
        boolean isCousins = isCousins(root, x, y);
        System.out.println(isCousins);
    }

    public static boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        TreeNode parentX = root;
        int depthX = getDepth(root, parentX, x, 0);
        parentX = CousinsOfBinaryTree.parentNode;

        TreeNode parentY = root;
        int depthY = getDepth(root, parentY, y, 0);
        parentY = CousinsOfBinaryTree.parentNode;

        if (depthX == depthY && parentX != parentY) {
            return true;
        }

        return false;
    }

    public static int getDepth(TreeNode root, TreeNode parent, int value, int depth) {
        if (root == null) {
            return - 1;
        }

        if (root.val == value) {
            CousinsOfBinaryTree.parentNode = parent;
            return depth;
        }

        int depthLeftSide = getDepth(root.left, root, value, depth + 1);
        int depthRightSide = getDepth(root.right, root, value, depth + 1);

        return Math.max(depthLeftSide, depthRightSide);
    }

}
