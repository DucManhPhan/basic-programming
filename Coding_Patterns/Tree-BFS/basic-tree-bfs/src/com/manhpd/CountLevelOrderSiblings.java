package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/m2YYxXDOJ03
 *
 * Given a binary tree, connect each node with its level order successor. The last node of each level should point to a null node.
 *
 * Example 1:
 * [[1],
 * [2; 3],
 * [4, 5; 6, 7]
 * ]
 *
 * Example 2:
 * [[12],
 * [7; 1],
 * [9; 10, 5]
 * ]
 *
 */
public class CountLevelOrderSiblings {

    public static void main(String[] args) {

    }

    public static void connect(TreeNode root) {
        // TODO: Write your code here
    }

    public static TreeNode build1stExample() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        return root;
    }

    public static TreeNode build2ndExample() {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(9);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode next;

        TreeNode(int x) {
            val = x;
            left = right = next = null;
        }

        // level order traversal using 'next' pointer
        public void printLevelOrder() {
            TreeNode nextLevelRoot = this;
            while (nextLevelRoot != null) {
                TreeNode current = nextLevelRoot;
                nextLevelRoot = null;
                while (current != null) {
                    System.out.print(current.val + " ");
                    if (nextLevelRoot == null) {
                        if (current.left != null)
                            nextLevelRoot = current.left;
                        else if (current.right != null)
                            nextLevelRoot = current.right;
                    }
                    current = current.next;
                }
                System.out.println();
            }
        }
    };

}
