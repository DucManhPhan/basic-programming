package com.manhpd;

public class RangeSumQueryMutable {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5};

        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }

    private static class NumArray {
        private SegmentTree tree;

        public NumArray(int[] nums) {
            if(nums.length > 0){
                tree = new SegmentTree(nums);
            }
        }

        public void update(int i, int val) {
            if(tree == null) return;
            tree.updateTree(tree.root, i, val);
        }

        public int sumRange(int i, int j) {
            if(tree == null) return 0;
            return tree.sumRange(tree.root, i, j);
        }

        public class Node {
            public Node leftNode;
            public Node rightNode;
            public int leftRange;
            public int rightRange;
            public int sum;     // define fields for problem

            public Node(int leftRange, int rightRange) {
                this.leftRange = leftRange;
                this.rightRange = rightRange;
            }
        }

        public class SegmentTree {
            public Node root;

            public SegmentTree(int[] nums) {
                this.root = new Node(0, nums.length - 1);
                this.buildSegmentTree(this.root, nums);
            }

            private Node buildSegmentTree(Node root, int[] nums) {
                if (root.leftRange == root.rightRange) {
                    root.sum = nums[root.leftRange];
                    return root;
                }

                // use this expression to reduce overflow when number is big
                int mid = root.leftRange + (root.rightRange - root.leftRange) / 2;
                Node left = this.buildSegmentTree(new Node(root.leftRange, mid), nums);
                Node right = this.buildSegmentTree(new Node(mid + 1, root.rightRange), nums);

                root.leftNode = left;
                root.rightNode = right;
                root.sum = left.sum + right.sum;

                return root;
            }

            public void updateTree(Node root, int i, int val) {
                if(root.leftRange == i && root.rightRange == i) {
                    root.sum = val;
                    return;
                }

                int md = root.leftRange + (root.rightRange - root.leftRange) / 2;
                if (i <= md) {
                    this.updateTree(root.leftNode, i, val);
                } else {
                    this.updateTree(root.rightNode, i, val);
                }

                root.sum = root.leftNode.sum + root.rightNode.sum;
            }

            public int sumRange(Node root, int i, int j) {
                if(root.leftRange == i && root.rightRange == j) {
                    return root.sum;
                }

                int md = root.leftRange + (root.rightRange - root.leftRange) / 2;
                if (j <= md) {
                    return sumRange(root.leftNode, i, j);
                }
                else if (i > md) {
                    return sumRange(root.rightNode, i, j);
                }

                return sumRange(root.leftNode, i, md) + sumRange(root.rightNode, md + 1, j);
            }
        }
    }

}
