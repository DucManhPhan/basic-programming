package com.manhpd.patternLongestCommonSubstring;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
//        int[] nums = {0, 1, 0, 3, 2, 3};
//        int[] nums = {7, 7, 7, 7, 7, 7, 7};
//        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
//        int[] nums = {1, 0, 1, 0, 3, 2, 3};
//        int[] nums = {3, 5, 6, 2, 5, 4, 9, 5, 6, 7, 8};
//        int[] nums = {3, 10, 2, 1, 20};

        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        int res = solution.lengthOfLISIII(nums);
        System.out.println(res);
    }

    /**
     * Using stack to solve - fail
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        Stack<Integer> res = new Stack<>();
        res.add(nums[0]);
        int max = 1;

        for (int i = 1; i < nums.length; ++i) {
            while (!res.isEmpty()) {
                int last = res.peek();
                if (!res.contains(nums[i]) && last >= nums[i]) {
                    res.pop();
                } else {
                    break;
                }
            }

            if (res.contains(nums[i])) {
                continue;
            }

            res.push(nums[i]);
            max = Math.max(max, res.size());
        }

        return max;
    }

    /**
     * Using the BST to solve - pass
     *
     * @param nums
     * @return
     */
    public int lengthOfLISI(int[] nums) {
        LisBst bst = new LisBst();
        int max = 1;
        bst.root = bst.add(nums[0], -1, bst.root);

        for (int i = 1; i < nums.length; ++i) {
            currentMax = 1;
            bst.add(nums[i], -1, bst.root);
            if (currentMax > max) {
                max = currentMax;
            }
        }

        return max;
    }

    private int currentMax = 1;

    private class LisBstNode {
        public int key;
        public int max;
        public int pre;
        public LisBstNode left, right;

        public LisBstNode(int key, int max, int pre, LisBstNode left,
                          LisBstNode right) {
            this.key = key;
            this.max = max;
            this.pre = pre;
            this.left = left;
            this.right = right;
        }
    }

    private class LisBst {
        private LisBstNode root;

        public LisBstNode add(int key, int pre, LisBstNode node) {
            if (node == null) {
                return new LisBstNode(key, currentMax, pre,
                        null, null);
            } else if (key > node.key) {
                if (currentMax < node.max + 1) {
                    currentMax = node.max + 1;
                }

                node.right = this.add(key, node.key, node.right);
            } else {
                node.left = this.add(key, node.key, node.left);
                if (currentMax > node.max) {
                    node.max = currentMax;
                }
            }

            return node;
        }
    }

    /**
     * Using bottom up approach for DP
     *
     * @param nums
     * @return
     */
    public int lengthOfLISIII(int[] nums) {
        int leng = nums.length;
        int[] L = new int[leng];
        L[0] = 1;

        int[] tracker = new int[leng];
        Arrays.fill(tracker, -1);

        for (int i = 1; i < leng; ++i) {
            int lmax = 0;
            int jmax = -1;

            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i] && lmax < L[j]) {
                    lmax = L[j];
                    jmax = j;
                }
            }

            L[i] = lmax + 1;
            tracker[i] = jmax;
        }

        this.printSubsequence(nums, L, tracker);

        return Arrays.stream(L).max().getAsInt();
    }

    public void printSubsequence(int[] nums, int[] L, int[] tracker) {
        int idxMaxElem = IntStream.range(0, L.length)
                                  .reduce((i, j) -> L[i] < L[j] ? j : i)
                                  .getAsInt();
        int i = idxMaxElem;
        int j = 0;

        while (true) {
            System.out.println(nums[i]);
            j = tracker[i];

            if (j == -1) break;
            i = j;
        }
    }

    /**
     * Using recursion to scan all cases
     */
    public int lengthOfLISII(int[] nums) {
        return this.maxLengthSubset(nums, -1, 0);
    }

    public int maxLengthSubset(int[] nums, int prevIdx, int currentIdx) {
        if (currentIdx == nums.length) {
            return 0;
        }

        int includedElemMaxLenSubset = 1;
        if (prevIdx <0 || nums[prevIdx] < nums[currentIdx]) {
            includedElemMaxLenSubset = 1 + this.maxLengthSubset(nums, currentIdx, currentIdx + 1);
        }

        int excludedElemMaxLenSubset = this.maxLengthSubset(nums, prevIdx, currentIdx + 1);
        return Math.max(includedElemMaxLenSubset, excludedElemMaxLenSubset);
    }

    /**
     * Using top-down approach of DP
     */
    public int lengthOfLISIV(int[] nums) {
        int[][] dp = new int[nums.length + 1][nums.length];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        return this.lengthOfLISIV(nums, -1, 0, dp);
    }

    public int lengthOfLISIV(int[] nums, int preIdx, int currentIdx, int[][] dp) {
        if (currentIdx == nums.length) {
            return 0;
        }

        if (dp[preIdx + 1][currentIdx] >= 0) {
            return dp[preIdx + 1][currentIdx];
        }

        int includedElemMaxLenSubset = 1;
        if (preIdx < 0 || nums[preIdx] < nums[currentIdx]) {
            includedElemMaxLenSubset = 1 + this.lengthOfLISIV(nums, currentIdx, currentIdx + 1, dp);
        }

        int excludedElemMaxLenSubset = this.lengthOfLISIV(nums, preIdx, currentIdx + 1, dp);
        dp[preIdx + 1][currentIdx] = Math.max(includedElemMaxLenSubset, excludedElemMaxLenSubset);

        return dp[preIdx + 1][currentIdx];
    }

    /**
     * Using DP with Binary Search
     *
     * @param nums
     * @return
     */
    public int lengthOfLISV(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            System.out.println("Binary search: " + i);
            if (i < 0) {
                i = -(i + 1);
            }

            dp[i] = num;
            if (i == len) {
                len++;
            }
        }

        return len;
    }

}
