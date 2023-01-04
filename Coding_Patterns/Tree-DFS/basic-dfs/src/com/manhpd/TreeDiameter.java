package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, find the length of its diameter.
 * The diameter of a tree is the number of nodes on the longest path between any two leaf nodes.
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

    private static int treeDiameter = 0;

    /**
     * Brute force algorithm but not AC this problem on LeetCode because the definition of diameter is different between here and Leetcode.
     * Leetcode uses the number of edges to define the diameter. Currently, we use the number of nodes.
     * So we need to adapt this issue.
     *
     * @param root
     * @return
     */
    public static int findDiameterV1(TreeNode root) {
        // find all leaf nodes
        List<TreeNode> leafNodes = new ArrayList<>();
        getAllLeafNodes(root, leafNodes);

        if (leafNodes.size() == 1) {
            int numNodes = numNodes(root, leafNodes.get(0));
            return numNodes;
        }

        // iterate from that list of leaf node and count the number nodes
        int maxDiameter = -1;
        for (int i = 0; i < leafNodes.size() - 1; ++i) {
            for (int j = i + 1; j < leafNodes.size(); ++j) {
                TreeNode leafNode1 = leafNodes.get(i);
                TreeNode leafNode2 = leafNodes.get(j);
                TreeNode commonAncestor = findCommonAncestor(root, leafNode1, leafNode2);

                System.out.println("LCA of nodes " + leafNode1.val + " and " + leafNode2.val + ": " + commonAncestor.val);

                int currentDiameter = countNodesBetweenTwoLeafNodes(commonAncestor, leafNode1, leafNode2);
                maxDiameter = maxDiameter < currentDiameter ?
                                            currentDiameter : maxDiameter;

                System.out.println("Max diameter: " + maxDiameter);
            }
        }

        return maxDiameter;
    }

    public static int findDiameterV2(TreeNode root) {
        calculateHeight(root);
        return treeDiameter;
    }

    public static int findDiameterV3(TreeNode root) {
        findHeight(root);
        return treeDiameter;
    }

    /**
     * According to the definition of the height of a binary tree, I think it's not correct.
     * So when using this solution in leetcode, it throws error.
     *
     * @param currentNode
     * @return
     */
    private static int calculateHeight(TreeNode currentNode) {
        if (currentNode == null)
            return 0;

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

    /**
     * Using this way is correct when applied on LeetCode
     *
     * @param root
     * @return
     */
    private static int findHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);

        treeDiameter = leftHeight + rightHeight + 2;
        return Math.max(leftHeight, rightHeight) + 1;
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

    private static TreeNode findCommonAncestor(TreeNode root, TreeNode leafNode1, TreeNode leafNode2) {
        if (root == null) {
            return null;
        }

        if (root.val == leafNode1.val || root.val == leafNode2.val) {
            return root;
        }

        TreeNode leafLcaNode = findCommonAncestor(root.left, leafNode1, leafNode2);
        TreeNode rightLcaNode = findCommonAncestor(root.right, leafNode1, leafNode2);

        if (leafLcaNode != null && rightLcaNode != null) {
            return root;
        }

        return leafLcaNode == null ? rightLcaNode : leafLcaNode;
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

        return numNodesFromFirstSide + numNodesFromSecondSide - 1;
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
        TreeNode root1 = buildExample1();
//        System.out.println("Tree Diameter: " + TreeDiameter.findDiameterV1(root1));
        System.out.println("Tree Diameter: " + TreeDiameter.findDiameterV2(root1));

//        TreeNode root2 = buildExample2();
//        System.out.println("Tree Diameter: " + TreeDiameter.findDiameterV1(root2));

//        TreeNode root3 = buildExample3();
//        System.out.println("Tree Diameter: " + TreeDiameter.findDiameterV1(root3));

//        TreeNode root4 = buildExample4();
//        System.out.println("Tree Diameter: " + TreeDiameter.findDiameterV1(root4));

//        TreeNode root5 = buildExample5();
//        System.out.println("Tree Diameter: " + TreeDiameter.findDiameterV1(root5));

//        TreeNode root6 = buildExample6();
//        System.out.println("Tree Diameter: " + TreeDiameter.findDiameterV1(root6));
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

        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);

        root.right.right.left = new TreeNode(9);

        root.right.left.right.left = new TreeNode(10);
        root.right.right.left.left = new TreeNode(11);

        return root;
    }

    private static TreeNode buildExample3() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        return root;
    }

    private static TreeNode buildExample4() {
        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);

        return root;
    }

    private static TreeNode buildExample5() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);

        return root;
    }

    private static TreeNode buildExample6() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(4);

        return root;
    }

}
