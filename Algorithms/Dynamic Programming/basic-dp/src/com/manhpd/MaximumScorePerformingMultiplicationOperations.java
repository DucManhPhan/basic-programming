package com.manhpd;

import java.util.Arrays;

public class MaximumScorePerformingMultiplicationOperations {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3};
//        int[] multipliers = {3, 2, 1};

        int[] nums = {-5,-3,-3,-2,7,1};
        int[] multipliers = {-10,-5,3,4,6};

        MaximumScorePerformingMultiplicationOperations solution = new MaximumScorePerformingMultiplicationOperations();
        System.out.println(solution.maximumScore(nums, multipliers));
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        int[] dp = new int[nums.length];
        int end = nums.length - 1;
        int start = 0;

        int idp = 0;
        for (start = 0; start <= end;) {
            int max1 = nums[start] * multipliers[idp];
            int max2 = nums[end] * multipliers[idp];
            int max = max1;

            if (max1 < max2) {
                --end;
                max = max2;
            } else {
                start++;
            }

            if (idp > 0) {
                dp[idp] = dp[idp - 1] + max;
            } else {
                dp[idp] = max;
            }

            idp++;
        }

        System.out.println(Arrays.toString(dp));
        return dp[nums.length - 1];
    }

}
