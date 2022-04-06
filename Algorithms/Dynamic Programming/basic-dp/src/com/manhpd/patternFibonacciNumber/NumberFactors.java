package com.manhpd.patternFibonacciNumber;

/**
 * Given a number ‘n’, implement a method to count how many possible ways there are to express ‘n’ as the sum of 1, 3, or 4.
 *
 * Example 1:
 * n : 4
 * Number of ways = 4
 * Explanation: Following are the four ways we can express 'n' : {1,1,1,1}, {1,3}, {3,1}, {4}
 *
 * Example 2:
 * n : 5
 * Number of ways = 6
 * Explanation: Following are the six ways we can express 'n' : {1,1,1,1,1}, {1,1,3}, {1,3,1}, {3,1,1},
 * {1,4}, {4,1}
 */
public class NumberFactors {

    public static void main(String[] args) {
        int n = 4;

        NumberFactors solution = new NumberFactors();
//        System.out.println(solution.countWays(n));
        System.out.println(solution.countWaysBottomUp(n));
    }

    public int countWays(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (n == 3) {
            return 2;
        }

        if (n == 4) {
            return 4;
        }

        return countWays(n - 1) + countWays(n - 3) + countWays(n - 4);
    }

    public int countWaysBottomUp(int n) {
        int idx = 0;
        int[] smallData = {0, 1, 1, 2, 4};
        if (n <= 0) {
            idx = 0;
        }

        idx = n;
        if (n <= 4) {
            return smallData[idx];
        }

        int[] dp = new int[n + 1];
        for (int i = 0; i <= 4; ++i) {
            dp[i] = smallData[i];
        }

        for (int i = 5; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 3] + dp[i - 4];
        }

        return dp[n];
    }

}
