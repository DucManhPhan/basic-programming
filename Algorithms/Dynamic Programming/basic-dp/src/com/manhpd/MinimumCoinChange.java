package com.manhpd;

import java.util.Arrays;

/**
 * Given an infinite supply of ‘n’ coin denominations and a total money amount, we are asked to find the minimum number of coins needed to make up that amount.
 *
 * Example 1:
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 2
 * Explanation: We need minimum of two coins {2,3} to make a total of '5'
 *
 * Example 2:
 * Denominations: {1,2,3}
 * Total amount: 11
 * Output: 4
 * Explanation: We need minimum four coins {2,3,3,3} to make a total of '11'
 */
public class MinimumCoinChange {

    public static void main(String[] args) {
        int[] denominations = {1, 2, 3};
        int total = 11;

        MinimumCoinChange solution = new MinimumCoinChange();
//        System.out.println(solution.countChangeRecursive(denominations, total));
        System.out.println(solution.countChangeDP(denominations, total));
    }

    public int countChangeRecursive(int[] denominations, int total) {
        return this.countChangeRecursive(denominations, total, 0);
    }

    /**
     * Using brute force algorithm
     *
     * @param denominations
     * @param total
     * @param currentIdx
     * @return
     */
    public int countChangeRecursive(int[] denominations, int total, int currentIdx) {
        if (currentIdx >= denominations.length || denominations.length == 0) {
            return Integer.MAX_VALUE;
        }

        if (total == 0) {
            return 0;
        }

        int count1 = Integer.MAX_VALUE;
        if (denominations[currentIdx] <= total) {
            int res = countChangeRecursive(denominations, total - denominations[currentIdx], currentIdx);
            if (res != Integer.MAX_VALUE) {
                count1 = res + 1;
            }
        }

        int count2 = countChangeRecursive(denominations, total, currentIdx + 1);

        return Math.min(count1, count2);
    }

    public int countChangeDP(int[] denominations, int total) {
        // using top-down approach
//        int[][] dp = new int[denominations.length][total + 1];
//        for (int i = 0; i < denominations.length; i++) {
//            Arrays.fill(dp[i], Integer.MAX_VALUE);
//        }
//
//        int res = this.countChangeTopDown(denominations, total, 0, dp);
//        for (int i = 0; i < denominations.length; ++i) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
//
//        return res;

        // using bottom-up approach
        return this.countChangeBottomUp(denominations, total);
    }

    /**
     * Using top-down algorithm
     *
     * @param denominations
     * @param total
     * @return
     */
    public int countChangeTopDown(int[] denominations, int total, int currentIdx, int[][] dp) {
        if (currentIdx >= denominations.length || denominations.length == 0 || total < 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[currentIdx][total] != Integer.MAX_VALUE) {
            return dp[currentIdx][total];
        }

        if (total == 0) {
            return 0;
        }

        System.out.println(total + " - " + currentIdx);
        int count1 = Integer.MAX_VALUE;
        if (denominations[currentIdx] <= total) {
            int res = countChangeTopDown(denominations, total - denominations[currentIdx], currentIdx, dp);
            if (res != Integer.MAX_VALUE) {
                count1 = res + 1;
            }
        }

        int count2 = countChangeTopDown(denominations, total, currentIdx + 1, dp);
        dp[currentIdx][total] = Math.min(count1, count2);

        return dp[currentIdx][total];
    }

    /**
     * Using bottom-up algorithm
     *
     * @param denominations
     * @param total
     * @return
     */
    public int countChangeBottomUp(int[] denominations, int total) {
        int[][] dp = new int[denominations.length][total + 1];

        for (int i = 0; i < denominations.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < denominations.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < denominations.length; ++i) {
            for (int t = 1; t <= total; ++t) {
                int num1 = Integer.MAX_VALUE;
                int num2 = Integer.MAX_VALUE;

                if (denominations[i] <= t && dp[i][t - denominations[i]] != Integer.MAX_VALUE) {
                    num1 = dp[i][t - denominations[i]] + 1;
                }

                if (i > 0) {
                    num2 = dp[i - 1][t];
                }

                dp[i][t] = Math.min(num1, num2);
            }
        }

        return dp[denominations.length - 1][total];
    }

}
