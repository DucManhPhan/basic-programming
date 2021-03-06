package com.manhpd;

import java.util.Arrays;

/**
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 * Example 1:
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 *
 * Example 2:
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 * Constraints:
 * 1 <= n <= 104
 *
 * References:
 * https://leetcode.com/problems/perfect-squares/discuss/984388/JAVA.-An-extension-of-Coin-Change-Problem. **
 * https://leetcode.com/problems/perfect-squares/discuss/1018680/Simple-DP-Java-Solution-unbounded-knapsack
 * https://leetcode.com/problems/perfect-squares/discuss/982248/Java-recursive-with-memoization
 * https://leetcode.com/problems/perfect-squares/discuss/1046247/Java-Bidirectional-BFS-(7ms)-faster-than-97
 * https://leetcode.com/problems/perfect-squares/discuss/1027524/Java-Solution-Using-Dynamic-programming(similar-to-LIS(O(n*n)-approach))
 * https://leetcode.com/problems/perfect-squares/discuss/1024514/BFS-very-simple-java-solution
 * https://leetcode.com/problems/perfect-squares/discuss/1024157/c%2B%2B-memoization-solution(similar-to-Coin-Change)
 * https://leetcode.com/problems/perfect-squares/discuss/1020411/Jake-DP-with-bottom-to-top-approach
 * https://leetcode.com/problems/perfect-squares/discuss/1020472/Jake-BFS
 * https://leetcode.com/problems/perfect-squares/discuss/1035088/java-dp-solution
 * https://leetcode.com/problems/perfect-squares/discuss/1041566/C%2B%2B-Simple-Solution-similar-to-Coin-Change-Problem
 * https://leetcode.com/problems/perfect-squares/discuss/1048406/Go-BFS-or-Readable
 *
 */
public class PerfectSquares {

    public static void main(String[] args) {
//        int n = 7168;
//        int n = 5778;
//        int n = 10;
        int n = 12;

        PerfectSquares solution = new PerfectSquares();
        System.out.println(solution.numSquaresIII(n));
    }

    /**
     * Using recursion for optimial substructure
     *
     * @param n
     * @return
     */
    public int numSquaresI(int n) {
        int endIdx = (int)Math.sqrt(n);
        return this.numSquaresI(n, endIdx);
    }

    public int numSquaresI(int n, int idx) {
        if (n < 0 || idx == 0) {
            return Integer.MAX_VALUE - 1;
        }

        if (n == (idx * idx)) {
            return 1;
        }

        int m = n - (idx * idx);
        int newIdx = idx - 1;
        if (m >= idx * idx) {
            newIdx = idx;
        }

        int minLength1 = 1 + this.numSquaresI(m, newIdx);
        int minLength2 = this.numSquaresI(n, idx - 1);

        return Math.min(minLength1, minLength2);
    }

    private boolean isSquareNumber(int start, int end, int key) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int square = mid * mid;

            if (square == key) {
                return true;
            } else if (key < square) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return false;
    }

    /**
     * Using top down approach of DP
     *
     * @param n
     * @return
     */
    public int numSquaresII(int n) {
        int[][] dp = new int[n + 1][n + 1];
        int endIdx = (int) Math.sqrt(n);

        for (int[] nums : dp) {
            Arrays.fill(nums, -1);
        }

        int res = this.numSquaresII(dp, n, endIdx);
        return res;
    }

    public int numSquaresII(int[][] dp, int n, int idx) {
        if (n < 0 || idx == 0) {
            return Integer.MAX_VALUE - 1;
        }

        if (dp[n][idx] != -1) {
            return dp[n][idx];
        }

        if (n == (idx * idx)) {
            return 1;
        }

        int m = n - (idx * idx);
        int newIdx = idx - 1;
        if (m >= idx * idx) {
            newIdx = idx;
        }

        int minLength1 = 1 + this.numSquaresII(dp, m, newIdx);
        int minLength2 = this.numSquaresII(dp, n, idx - 1);
        dp[n][idx] = Math.min(minLength1, minLength2);

        return dp[n][idx];
    }

    /**
     * Using Bottom up approach DP
     *
     * @param n
     * @return
     */
    public int numSquaresIII(int n) {
        int endIdx = (int) Math.sqrt(n);
        int[][] dp = new int[n + 1][endIdx + 1];

        for (int i = 1; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE - 1);
            dp[i][1] = i;
        }

        for (int i = 1; i <= endIdx; ++i) {
            dp[i * i][i] = 1;
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = endIdx; j >= 1; --j) {
                if (dp[i][j] != Integer.MAX_VALUE - 1) {
                    break;
                }

                int m = i - (j * j);
                int newIdx = j - 1;
                int minLength1 = 0;

                if (m < 0 || newIdx < 0) {
                    minLength1 = Integer.MAX_VALUE - 1;
                } else {
                    if (m >= j * j) {
                        newIdx = j;
                    }

                    minLength1 = dp[m][newIdx];
                }

                dp[i][j] = Math.min(1 + minLength1, dp[i][j - 1]);
            }
        }

        return dp[n][endIdx];
    }

}
