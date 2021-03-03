package com.manhpd;

import java.util.Arrays;

/**
 * We are given a ribbon of length ‘n’ and a set of possible ribbon lengths.
 * We need to cut the ribbon into the maximum number of pieces that comply with the above-mentioned possible lengths.
 * Write a method that will return the count of pieces.
 *
 * Example 1:
 * n: 5
 * Ribbon Lengths: {2,3,5}
 * Output: 2
 * Explanation: Ribbon pieces will be {2,3}.
 *
 * Example 2:
 * n: 7
 * Ribbon Lengths: {2,3}
 * Output: 3
 * Explanation: Ribbon pieces will be {2,2,3}.
 *
 * Example 3:
 * n: 13
 * Ribbon Lengths: {3,5,7}
 * Output: 3
 * Explanation: Ribbon pieces will be {3,3,7}.
 */
public class MaximumRibbonCut {

    public static void main(String[] args) {
//        int[] ribbonLengths = {2, 3, 5};
//        int total = 5;

//        int[] ribbonLengths = {2, 3};
//        int total = 7;

        int[] ribbonLengths = {3, 5, 7};
        int total = 13;

        MaximumRibbonCut solution = new MaximumRibbonCut();
        System.out.println(solution.countRibbonPieces(ribbonLengths, total));
    }

    public int countRibbonPieces(int[] ribbonLengths, int total) {
        // using recursive
//        return this.countRibbonPiecesRecursive(ribbonLengths, total, 0);

        // using DP
        return this.countRibbonPiecesDP(ribbonLengths, total);
    }

    /**
     * Using recursive algorithm
     *
     * @param ribbonLengths
     * @param total
     * @param currentIdx
     * @return
     */
    public int countRibbonPiecesRecursive(int[] ribbonLengths, int total, int currentIdx) {
        if (currentIdx >= ribbonLengths.length || ribbonLengths.length == 0) {
            return Integer.MIN_VALUE;
        }

        if (total == 0) {
            return 0;
        }

        int num1 = Integer.MIN_VALUE;
        if (ribbonLengths[currentIdx] <= total) {
            int res = countRibbonPiecesRecursive(ribbonLengths, total - ribbonLengths[currentIdx], currentIdx);
            if (res != Integer.MIN_VALUE) {
                num1 = res + 1;
            }
        }

        int num2 = countRibbonPiecesRecursive(ribbonLengths, total, currentIdx + 1);
        return Math.max(num1, num2);
    }

    /**
     * Using DP
     *
     * @param ribbonLengths
     * @param total
     * @return
     */
    public int countRibbonPiecesDP(int[] ribbonLengths, int total) {
        // using top down approach
//        int[][] dp = new int[ribbonLengths.length][total + 1];
//
//        for (int i = 0; i < ribbonLengths.length; i++) {
//            Arrays.fill(dp[i], Integer.MIN_VALUE);
//        }
//
//        return this.countRibbonPiecesTopDown(ribbonLengths, total, 0, dp);

        // using bottom up approach
        return this.countRibbonPiecesBottomUp(ribbonLengths, total);
    }

    /**
     * Using top down approach
     *
     * @param ribbonLengths
     * @param total
     * @param currentIdx
     * @param dp
     * @return
     */
    public int countRibbonPiecesTopDown(int[] ribbonLengths, int total, int currentIdx, int[][] dp) {
        if (currentIdx >= ribbonLengths.length || ribbonLengths.length == 0) {
            return Integer.MIN_VALUE;
        }

        if (dp[currentIdx][total] != Integer.MIN_VALUE) {
            return dp[currentIdx][total];
        }

        if (total == 0) {
            return 0;
        }

        int num1 = Integer.MIN_VALUE;
        if (ribbonLengths[currentIdx] <= total) {
            int res = countRibbonPiecesTopDown(ribbonLengths, total - ribbonLengths[currentIdx], currentIdx, dp);
            if (res != Integer.MIN_VALUE) {
                num1 = res + 1;
            }
        }

        int num2 = countRibbonPiecesTopDown(ribbonLengths, total, currentIdx + 1, dp);
        return Math.max(num1, num2);
    }

    /**
     * Using bottom up approach
     *
     * @param ribbonLengths
     * @param total
     * @return
     */
    public int countRibbonPiecesBottomUp(int[] ribbonLengths, int total) {
        int[][] dp = new int[ribbonLengths.length][total + 1];

        for (int i = 0; i < ribbonLengths.length; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        for (int i = 0; i < ribbonLengths.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i < ribbonLengths.length; ++i) {
            for (int t = 1; t <= total; ++t) {
                int num1 = Integer.MIN_VALUE;
                int num2 = Integer.MIN_VALUE;

                if (ribbonLengths[i] <= t && dp[i][t - ribbonLengths[i]] != Integer.MIN_VALUE) {
                    num1 = dp[i][t - ribbonLengths[i]] + 1;
                }

                if (i > 0) {
                    num2 = dp[i - 1][t];
                }

                dp[i][t] = Math.max(num1, num2);
            }
        }

        return dp[ribbonLengths.length - 1][total];
    }
}
