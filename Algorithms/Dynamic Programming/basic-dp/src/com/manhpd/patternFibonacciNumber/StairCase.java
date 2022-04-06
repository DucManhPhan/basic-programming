package com.manhpd.patternFibonacciNumber;

/**
 * Given a stair with ‘n’ steps, implement a method to count how many possible ways are there to reach the top of the staircase,
 * given that, at every step you can either take 1 step, 2 steps, or 3 steps.
 *
 * Example 1:
 * Number of stairs (n) : 3
 * Number of ways = 4
 * Explanation: Following are the four ways we can climb : {1,1,1}, {1,2}, {2,1}, {3}
 *
 * Example 2:
 * Number of stairs (n) : 4
 * Number of ways = 7
 * Explanation: Following are the seven ways we can climb : {1,1,1,1}, {1,1,2}, {1,2,1}, {2,1,1},
 * {2,2}, {1,3}, {3,1}
 */
public class StairCase {

    public static void main(String[] args) {
//        int n = 3;
//        int n = 4;
        int n = 10;

        StairCase solution = new StairCase();
        System.out.println(solution.countWays(n));
    }

    public int countWays(int n) {
        // using recursive approach
//        return this.countWaysRecursive(n);

        // using bottom up approach
        return this.countWaysBottomUp(n);
    }

    public int countWaysRecursive(int n) {
        if (n == 1 || n== 2) {
            return n;
        }

        if (n == 3) {
            return n + 1;
        }

        return countWaysRecursive(n - 1) + countWaysRecursive(n - 2) + countWaysRecursive(n - 3);
    }

    /**
     * Using bottom up approach
     *
     * @param n
     * @return
     */
    public int countWaysBottomUp(int n) {
        if (n == 1 || n == 2) {
            return n;
        }

        if (n == 3) {
            return n + 1;
        }

        int way1 = 1;
        int way2 = 2;
        int way3 = 4;

        int res = 0;
        for (int i = 4; i <= n; ++i) {
            res = way1 + way2 + way3;

            way1 = way2;
            way2 = way3;
            way3 = res;
        }

        return res;
    }

}
