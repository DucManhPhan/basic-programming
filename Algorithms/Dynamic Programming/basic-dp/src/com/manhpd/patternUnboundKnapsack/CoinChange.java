package com.manhpd.patternUnboundKnapsack;

/**
 * Given an infinite supply of ‘n’ coin denominations and a total money amount,
 * we are asked to find the total number of distinct ways to make up that amount.
 *
 * Example:
 * Denominations: {1,2,3}
 * Total amount: 5
 * Output: 5
 * Explanation: There are five ways to make the change for '5', here are those ways:
 *   1. {1,1,1,1,1}
 *   2. {1,1,1,2}
 *   3. {1,2,2}
 *   4. {1,1,3}
 *   5. {2,3}
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] denominations = {1, 2, 3};
        int total = 5;

//        int[] denominations = {3};
//        int total = 2;
        CoinChange solution = new CoinChange();
        System.out.println(solution.countChange(denominations, total));
        System.out.println(solution.countChangeRecursion(denominations, total));
    }

    /**
     * Using Recursion algorithm
     *
     * @param coins
     * @param amount
     * @return
     */
    public int countChangeRecursion(int[] coins, int amount) {
        return this.countChange(coins, amount, 0);
    }

    private int countChange(int[] coins, int amount, int idx) {
        // base case
        if (amount == 0) {
            return 1;
        }

        if (amount < coins[idx] || idx >= coins.length) {
            return 0;
        }

        // choose the coin at idx index
        int num1 = countChange(coins, amount - coins[idx], idx);

        // not choose the coin at idx
        int num2 = 0;
        if (idx < coins.length - 1) {
            num2 = countChange(coins, amount, idx + 1);
        }

        System.out.println("(" + amount + ", " + idx + ") = " + (num1 + num2));
        return num1 + num2;
    }

    /**
     * Using Bottom-up DP
     *
     * @param coins
     * @param amount
     * @return
     */
    public int countChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return amount == 0 ? 1 : 0;
        }

        int[][] dp = new int[coins.length][amount + 1];

        for (int i = 0; i < coins.length; ++i) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < coins.length; ++i) {
            for (int t = 1; t <= amount; ++t) {
                if (coins[i] <= t) {
                    dp[i][t] += dp[i][t - coins[i]];
                }

                if (i > 0) {
                    dp[i][t] += dp[i - 1][t];
                }
            }
        }

        return dp[coins.length - 1][amount];
    }

}
