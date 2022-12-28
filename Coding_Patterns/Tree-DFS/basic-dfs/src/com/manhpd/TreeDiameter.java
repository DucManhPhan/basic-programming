package com.manhpd;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, find the length of its diameter. The diameter of a tree is the number of nodes on the longest path between any two leaf nodes.
 * The diameter of a tree may or may not pass through the root.
 *
 * Note: You can always assume that there are at least two leaf nodes in the given tree.
 *
 * Example 1:
 * Tree: [1], [2, 3], [[4], [5, 6]]
 * Output: 5
 * Explanation: The diameter of the tree is: [4, 2, 1, 3, 6]
 *
 * Example 2:
 * Tree: [1], [2, 3], [[], [5, 6]], [[7, 8], [9]], [[], [10], [11]]
 * Output: 7
 * Explanation: The diameter of the tree is: [10, 8, 5, 3, 6, 9, 11]
 *
 */
public class TreeDiameter {

    /**
     * Brute force algorithm
     *
     * @param root
     * @return
     */
    public static int findDiameterV1(TreeNode root) {
        // find all leaf nodes
        List<TreeNode> leafNodes = new ArrayList<>();
        getAllLeafNodes(root, leafNodes);

        // iterate from that list of leaf node and count the number nodes
        int maxDiameter = -1;
        for (int i = 0; i < leafNodes.size() - 1; ++i) {
            for (int j = i + 1; j < leafNodes.size(); ++j) {
                TreeNode leafNode1 = leafNodes.get(i);
                TreeNode leafNode2 = leafNodes.get(j);
                TreeNode commonAncestor = findCommonAncestor(leafNode1, leafNode2);

                int currentDiameter = countNodesBetweenTwoLeafNodes(commonAncestor, leafNode1, leafNode2);
                maxDiameter = maxDiameter < currentDiameter ?
                                            currentDiameter : maxDiameter;
            }
        }

        return maxDiameter;
    }

    private static void getAllLeafNodes(TreeNode root, List<TreeNode> leafNodes) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            leafNodes.add(root);
            return;
        }

        getAllLeafNodes(root.left, leafNodes);
        getAllLeafNodes(root.right, leafNodes);
    }

    private static TreeNode findCommonAncestor(TreeNode leafNode1, TreeNode leafNode2) {
        return null;
    }

    /**
     *
     * @param root
     * @param leafNode1
     * @param leafNode2
     * @return
     */
    private static int countNodesBetweenTwoLeafNodes(TreeNode root, TreeNode leafNode1, TreeNode leafNode2) {
        int numNodesFromFirstSide = numNodes(root, leafNode1);
        int numNodesFromSecondSide = numNodes(root, leafNode2);

        return numNodesFromFirstSide + numNodesFromSecondSide;
    }

    private static int numNodes(TreeNode root, TreeNode leafNode) {
        if (root == null) {
            return 0;
        }

        if (root == leafNode) {
            return 1;
        }

        int count1 = numNodes(root.left, leafNode);
        int count2 = numNodes(root.right, leafNode);

        if (count1 > 0) {
            return count1 + 1;
        } else if (count2 > 0) {
            return count2 + 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameterV1(root));
//        root.left.left = null;
//        root.right.left.left = new TreeNode(7);
//        root.right.left.right = new TreeNode(8);
//        root.right.right.left = new TreeNode(9);
//        root.right.left.right.left = new TreeNode(10);
//        root.right.right.left.left = new TreeNode(11);
//        System.out.println("Tree Diameter: " + TreeDiameter.findDiameterV1(root));
    }

}
