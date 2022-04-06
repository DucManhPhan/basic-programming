package com.manhpd.common;

/**
 * https://www.geeksforgeeks.org/tiling-problem/
 */
public class TilingProblem {

    private int count = 0;

    public static void main(String[] args) {
        TilingProblem solution = new TilingProblem();
        int n = 4;

//        int res = solution.count(n);
//        System.out.println(res);

        solution.countAllWays(n);
    }

    /**
     * Use bottom up for DP
     * I feel it doesn't count right away
     *
     * @param n
     * @return
     */
    private int count(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = 1;

        for (int i = 2; i <= n; ++i) {
            res[i] = res[i - 1] + res[i - 2];
        }

        return res[n];
    }

    /**
     * List all ways in the reality
     *
     * @param n
     */
    private void countAllWays(int n) {
        int[][] res = new int[2][n];
        int[] directions = new int[]{0, 1};

        count(res, directions, -1, 0, n);
        System.out.println("Number of ways: " + this.count);
    }

    private void count(int[][] res, int[] directions, int currentDirection, int startIdx, int n) {
        return;
    }

}
