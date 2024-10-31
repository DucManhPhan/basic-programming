package com.manhpd.java;

/**
 * Given an array of positive numbers and a positive number ‘k,’ find the maximum sum of any contiguous subarray of size ‘k’.
 *
 * Example 1:
 *
 * Input: [2, 1, 5, 1, 3, 2], k=3
 * Output: 9
 * Explanation: Subarray with maximum sum is [5, 1, 3].
 *
 * Example 2:
 *
 * Input: [2, 3, 4, 1, 5], k=2
 * Output: 7
 * Explanation: Subarray with maximum sum is [3, 4].
 */
public class MaximumSumSubarrayWithSizeK {

    public static void main(String[] args) {
        // Example 1
//        int[] arr = {2, 1, 5, 1, 3, 2};
//        int k = 3;
//        int expected = 9;

        // Example 2
        int[] arr = {2, 3, 4, 1, 5};
        int k = 2;
        int expected = 7;

        int result = findMaxSumSubArray(k, arr);
        System.out.printf("Result: %d, Expectation: %d", result, expected);
    }

    public static int findMaxSumSubArray(int k, int[] arr) {
        int windowStart = 0;
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int windowEnd = 0; windowEnd < arr.length; ++windowEnd) {
            sum += arr[windowEnd];

            if (windowEnd - windowStart == k - 1) {
                maxSum = Math.max(sum, maxSum);

                sum -= arr[windowStart];
                ++windowStart;
            }
        }

        return maxSum;
    }
}
