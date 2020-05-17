package com.manhpd;

/**
 * Calculate the depth of the tree
 */
public class DepthOfTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);

        int res = getDepth(root);
        System.out.println(res);
    }

    public static int getDepth(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int depthLeft = getDepth(root.left);
        int depthRight = getDepth(root.right);

        return Math.max(depthLeft + 1, depthRight + 1);
    }

}
