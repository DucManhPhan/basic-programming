package com.manhpd;

import java.util.Objects;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Example 3:
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 */
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        preprocessTree(root, new TreeNode(Integer.MIN_VALUE));
        return lca(p, q);
    }

    public TreeNode lowestCommonAncestorV1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode leftLcaNode = lowestCommonAncestorV1(root.left, p, q);
        TreeNode rightLcaNode = lowestCommonAncestorV1(root.right, p, q);

        if (leftLcaNode != null && rightLcaNode != null) {
            return root;
        }

        return leftLcaNode != null ? leftLcaNode : rightLcaNode;
    }

    /**
     * Calculation of the depth and the parent of each node
     *
     * @param root
     */
    private void preprocessTree(TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }

        root.depth = parent.depth + 1;
        root.parent = parent;

        preprocessTree(root.left, root);
        preprocessTree(root.right, root);
    }

    /**
     * Find the Lowest Common Ancestor Node of a tree
     *
     * @param p
     * @param q
     * @return
     */
    private TreeNode lca(TreeNode p, TreeNode q) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(q);

        while (p.depth != q.depth) {
            if (p.depth > q.depth) {
                p = p.parent;
            } else {
                q = q.parent;
            }
        }

        while (p != q) {
            p = p.parent;
            q = q.parent;
        }

        return p;
    }

    private TreeNode findNode(TreeNode root, int value) {
        if (root == null) {
            return null;
        }

        if (root.val == value) {
            return root;
        }

        TreeNode tmp1 = findNode(root.left, value);
        TreeNode tmp2 = findNode(root.right, value);

        return tmp1 != null ? tmp1 : tmp2;
    }

    public static void main(String[] args) {
        LowestCommonAncestor lca = new LowestCommonAncestor();
        TreeNode root = buildExample1();
        TreeNode p = lca.findNode(root, 5);
        TreeNode q = lca.findNode(root, 4);

//        TreeNode res = lca.lowestCommonAncestor(root, p, q);
        TreeNode res = lca.lowestCommonAncestorV1(root, p, q);
        System.out.println("The LCA: " + res.val);
    }

    public static TreeNode buildExample1() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        return root;
    }

}
