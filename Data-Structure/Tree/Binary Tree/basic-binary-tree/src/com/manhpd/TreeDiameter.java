package com.manhpd;

/**
 * Given a binary tree, find the length of its diameter.
 * The diameter of a tree is the number of nodes on the longest path between any two leaf nodes.
 * The diameter of a tree may or may not pass through the root.
 *
 *
 *
 */
public class TreeDiameter {

    private static int treeDiameter = 0;

    public static int findDiameter(TreeNode root) {
        calculateHeight(root);
        return treeDiameter;
    }

    /**
     * When applied data in example 1, the correct diameter is 8, but we calculated it to 7. Because we always used from root node.
     *
     * @param currentNode
     * @return
     */
    private static int calculateHeight(TreeNode currentNode) {
        if (currentNode == null)
            return -1;

        int leftTreeHeight = calculateHeight(currentNode.left);
        int rightTreeHeight = calculateHeight(currentNode.right);

        // if the current node doesn't have a left or right subtree, we can't have
        // a path passing through it, since we need a leaf node on each side
        if (leftTreeHeight != 0 && rightTreeHeight != 0) {

            // diameter at the current node will be equal to the height of left subtree +
            // the height of right sub-trees + '1' for the current node
            int diameter = leftTreeHeight + rightTreeHeight + 1;

            // update the global tree diameter
            treeDiameter = Math.max(treeDiameter, diameter);
        }

        // height of the current node will be equal to the maximum of the heights of
        // left or right subtrees plus '1' for the current node
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = buildExample1();

        System.out.println("Diameter: " + findDiameter(root));
    }

    private static TreeNode buildExample1() {
        TreeNode root = new TreeNode(4);

        root.left = new TreeNode(-7);
        root.right= new TreeNode(-3);

        root.right.left = new TreeNode(-9);
        root.right.right = new TreeNode(-3);

        root.right.left.left = new TreeNode(9);
        root.right.left.right = new TreeNode(-7);
        root.right.right.left = new TreeNode(-4);

        root.right.left.left.left = new TreeNode(6);
        root.right.left.left.left.left = new TreeNode(0);
        root.right.left.left.left.left.left = new TreeNode(-1);
        root.right.left.left.left.right = new TreeNode(6);
        root.right.left.left.left.right.left = new TreeNode(-4);

        root.right.left.right.left = new TreeNode(-6);
        root.right.left.right.left.left = new TreeNode(5);
        root.right.left.right.right = new TreeNode(-6);
        root.right.left.right.right.left = new TreeNode(9);
        root.right.left.right.right.left.left = new TreeNode(-2);

        return root;
    }

    private static TreeNode buildExample2() {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        return root;
    }

}
