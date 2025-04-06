package com.manhpd.patternKnapsack01;

import java.util.Arrays;

/**
 * Given a set of positive numbers, partition the set into two subsets with a minimum difference between their subset sums.
 *
 * Example 1:
 * Input: {1, 2, 3, 9}
 * Output: 3
 * Explanation: We can partition the given set into two subsets where the minimum absolute difference
 * between the sum of numbers is '3'. Following are the two subsets: {1, 2, 3} & {9}.
 *
 * Example 2:
 * Input: {1, 2, 7, 1, 5}
 * Output: 0
 * Explanation: We can partition the given set into two subsets where the minimum absolute difference
 * between the sum of numbers is '0'. Following are the two subsets: {1, 2, 5} & {7, 1}.
 *
 * Example 3:
 * Input: {1, 3, 100, 4}
 * Output: 92
 * Explanation: We can partition the given set into two subsets where the minimum absolute difference
 * between the sum of numbers is '92'. Here are the two subsets: {1, 3, 4} & {100}.
 *
 */
public class MinimumSubsetSumDifference {

    private static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // Example 1
        int[] num = {1, 2, 3, 9};
        int expected = 3;

        // Example 2
//        int[] num = {1, 2, 7, 1, 5};
//        int expected = 0;

        // Example 3
//        int[] num = {1, 3, 100, 4};
//        int expected = 92;

//        int res = canPartitionV1(num);
        int res = canPartitionV2(num);
        System.out.println("res = " + res + ", expected: " + expected);
    }

    /**
     * Recursion
     *
     * @param num
     * @return
     */
    public static int canPartitionV1(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }

        int sum1 = 0;
        int sum2 = 0;

        return canPartitionV1(num, 0, sum1, sum2);
    }

    private static int canPartitionV1(int[] num, int idx, int sum1, int sum2) {
        if (idx >= num.length) {
            return Math.abs(sum1 - sum2);
        }

        int diff1 = canPartitionV1(num, idx + 1, num[idx] + sum1, sum2);
        int diff2 = canPartitionV1(num, idx + 1, sum1, num[idx] + sum2);

        return Math.min(diff1, diff2);
    }

    /**
     * Use DP based on Memoization
     *
     * @param num
     * @return
     */
    public static int canPartitionV2(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }

        int sum = Arrays.stream(num).sum();
        int sum1 = 0;
        int sum2 = 0;
        int[][] dp = new int[num.length + 1][sum + 1];
        int res = canPartitionV2(num, 0, sum1, sum2, dp);

        return res;
    }

    private static int canPartitionV2(int[] num, int idx, int sum1, int sum2, int[][] dp) {
        if (idx >= num.length) {
            return Math.abs(sum1 - sum2);
        }

        int diff1 = canPartitionV2(num, idx + 1, num[idx] + sum1, sum2, dp);
        int diff2 = canPartitionV2(num, idx + 1, sum1, num[idx] + sum2, dp);

        dp[idx][sum1] = Math.min(diff1, diff2);
        return dp[idx][sum1];
    }

    /**
     * Use DP based on Tabulation
     *
     * @param num
     * @return
     */
    public static int canPartitionV3(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }

        int sum = Arrays.stream(num).sum();
        int[][] dp = new int[num.length][sum + 1];



        return -1;
    }
}
