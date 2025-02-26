package com.manhpd.patternKnapsack01;

import java.util.Arrays;

/**
 * Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both the subsets is equal.
 *
 * Input: {1, 2, 3, 4}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
 *
 * Input: {1, 1, 3, 4, 7}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}
 *
 * Input: {2, 3, 4, 6}
 * Output: False
 * Explanation: The given set cannot be partitioned into two subsets with equal sum.
 *
 */
public class EqualSubsetSumPartition {

    public static void main(String[] args) {
        // Example 1
//        int[] nums = {1, 2, 3, 4};
//        boolean res = true;

        // Example 2
//        int[] nums = {1, 1, 3, 4, 7};
//        boolean res = true;

        // Example 3
        int[] nums = {2, 3, 4, 6};
        boolean res = false;

//        boolean canPartition = canPartitionV1(nums);
//        boolean canPartition = canPartitionV2(nums);
        boolean canPartition = canPartitionV3(nums);

        System.out.println(canPartition);
    }

    /**
     * Using recursion version to solve it.
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV1(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        boolean canPartition = canPartitionV1(num, 0, sum/2);
        return canPartition;
    }

    public static boolean canPartitionV1(int[] num, int currentIndex, int target) {
        if (currentIndex >= num.length || target < 0) {
            return false;
        }

        if (target == 0) {
            return true;
        }

        boolean isAdded = false;
        boolean isSkipped = false;
        if (num[currentIndex] <= target) {
            isAdded = canPartitionV1(num, currentIndex + 1, target - num[currentIndex]);
        }

        isSkipped = canPartitionV1(num, currentIndex + 1, target);
        return isAdded || isSkipped;
    }

    /**
     * Use Dynamic Programming with Memoization
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV2(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        Boolean[][] dp = new Boolean[num.length][(sum/2) + 1];

        boolean canPartition = canPartitionV2(num, dp, 0, sum/2);
        return canPartition;
    }

    private static boolean canPartitionV2(int[] num, Boolean[][] dp, int idx, int halfSum) {
        if (idx >= num.length || halfSum < 0) {
            return false;
        }

        if (halfSum == 0) {
            dp[idx][halfSum] = true;
            return true;
        }

        if (dp[idx][halfSum] != null) {
            return dp[idx][halfSum];
        }

        boolean isAdded = false;
        boolean isSkipped = false;

        if (num[idx] <= halfSum) {
            isAdded = canPartitionV2(num, dp, idx + 1, halfSum - num[idx]);
        }

        isSkipped = canPartitionV2(num, dp, idx + 1, halfSum);
        dp[idx][halfSum] = isAdded || isSkipped;

        return dp[idx][halfSum];
    }

    /**
     * Using Dynamic Programming with Tabulation
     *
     * @param num
     * @return
     */
    public static boolean canPartitionV3(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        int halfSum = sum/2;
        boolean[][] dp = new boolean[num.length][halfSum + 1];

        // when sum = 0, we always have a subset's sum = 0 --> true
        for (int i = 1; i < num.length; ++i) {
                dp[i][0] = true;
        }

        // If the nums array that has only one element, the value at dp's index will be true
        // if its value = sum
        for (int i = 1; i <= halfSum; ++i) {
            dp[0][i] = (num[0] == i ? true : false);
        }

        boolean res = false;
        for (int i = 1; i < num.length; ++i) {
            for (int j = 1; j <= halfSum; ++j) {
                res = false;

                if (num[i] <= j) {
                    res = dp[i - 1][j - num[i]];
                }

                dp[i][j] = res || dp[i - 1][j];
            }
        }

        return dp[num.length - 1][halfSum];
    }
}
