package com.manhpd.common;

/**
 * You are given two integer arrays nums and multipliers of size n and m respectively, where n >= m. The arrays are 1-indexed.
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (1-indexed), you will:
 * <p>
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Remove x from the array nums.
 * Return the maximum score after performing m operations.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 * <p>
 * Example 2:
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 * <p>
 * <p>
 * Constraints:
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 103
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 */
public class MaximumScorePerformingMultiplicationOperations {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
//        int[] multipliers = {3, 2, 1};

        int[] nums = {-5, -3, -3, -2, 7, 1};
        int[] multipliers = {-10, -5, 3, 4, 6};

        MaximumScorePerformingMultiplicationOperations solution = new MaximumScorePerformingMultiplicationOperations();
//        System.out.println(solution.maximumScore(nums, multipliers));
        System.out.println(solution.maximumScoreDP(nums, multipliers));
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        // using recursion approach
//        return this.maximumScoreRecursive(nums, multipliers, 0, 0, nums.length - 1);

        // using DP approach
        return this.maximumScoreDP(nums, multipliers);
    }

    /**
     * Using recursion approach
     *
     * @param nums
     * @param multipliers
     * @param currentOperation
     * @param start
     * @param end
     * @return
     */
    public int maximumScoreRecursive(int[] nums, int[] multipliers, int currentOperation, int start, int end) {
        if (currentOperation >= multipliers.length || start > end) {
            return 0;
        }

        int num1 = multipliers[currentOperation] * nums[start];
        int num2 = multipliers[currentOperation] * nums[end];

        int max1 = num1 + maximumScoreRecursive(nums, multipliers, currentOperation + 1, start + 1, end);
        int max2 = num2 + maximumScoreRecursive(nums, multipliers, currentOperation + 1, start, end - 1);
        return Math.max(max1, max2);
    }

    /**
     * Using DP
     *
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScoreDP(int[] nums, int[] multipliers) {
        // using top down approach
//        int[][][] dp = new int[multipliers.length][nums.length][nums.length];
//        return this.maximumScoreTopDown(nums, multipliers, dp, 0, 0, nums.length - 1);

        int[][] dp = new int[nums.length][nums.length];
        return this.maximumScoreTopDown(nums, multipliers, dp, 0, nums.length - 1);

        // using bottom up approach
//        return this.maximumScoreBottomUp(nums, multipliers);
    }

    public int maximumScoreTopDown(int[] nums, int[] multipliers, int[][][] dp, int currentOp, int start, int end) {
        if (currentOp >= multipliers.length || start > end) {
            return 0;
        }

        if (dp[currentOp][start][end] != 0) {
            return dp[currentOp][start][end];
        }

        int num1 = multipliers[currentOp] * nums[start];
        int num2 = multipliers[currentOp] * nums[end];

        int max1 = num1 + maximumScoreTopDown(nums, multipliers, dp, currentOp + 1, start + 1, end);
        int max2 = num2 + maximumScoreTopDown(nums, multipliers, dp, currentOp+ 1, start, end - 1);
        dp[currentOp][start][end] = Math.max(max1, max2);

        return dp[currentOp][start][end];
    }

    public int maximumScoreTopDown(int[] nums, int[] multipliers, int[][] dp, int start, int end) {
        int currentOp = nums.length - (end - start + 1);

        if (currentOp >= multipliers.length || start > end) {
            return 0;
        }

        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        int num1 = multipliers[currentOp] * nums[start];
        int num2 = multipliers[currentOp] * nums[end];

        int max1 = num1 + maximumScoreTopDown(nums, multipliers, dp,start + 1, end);
        int max2 = num2 + maximumScoreTopDown(nums, multipliers, dp, start, end - 1);
        dp[start][end] = Math.max(max1, max2);

        return dp[start][end];
    }

    public int maximumScoreBottomUp(int[] nums, int[] multipliers) {
        int[][] dp = new int[nums.length][nums.length];

        for (int start = 0; start < nums.length; ++start) {
            for (int end = nums.length - 1; end >= 0; --end) {
                int currentOp = nums.length - (end - start + 1);

                dp[start][end] = Math.max(multipliers[currentOp] * nums[start] + dp[start + 1][end],
                                          multipliers[currentOp] * nums[end] + dp[start][end]);
            }
        }

        return dp[0][0];
    }
}
