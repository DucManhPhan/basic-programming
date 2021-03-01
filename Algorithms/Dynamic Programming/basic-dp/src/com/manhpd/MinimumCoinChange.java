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
        int total = 5;

        MinimumCoinChange solution = new MinimumCoinChange();
        System.out.println(solution.countChange(denominations, total));
    }

    public int countChangeRecursive(int[] denominations, int total) {

        return -1;
    }

    public int countChangeRecursive(int[] denominations, int total, int currentIdx, int num) {
        if (currentIdx >= denominations.length) {
            return 0;
        }


    }

    public int countChange(int[] denominations, int total) {
        int[][] dp = new int[denominations.length][total + 1];

        for (int i = 0; i < denominations.length; i++) {
//            dp[i][0] = 0;
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i <= total; ++i) {
            dp[0][i] = 1;
        }

        for (int i = 0; i < denominations.length; ++i) {
            for (int t = 1; t <= total; ++t) {
                int num1 = 0;
                int num2 = 0;

                if (denominations[i] <= t) {
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
