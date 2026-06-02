package com.manhpd.java;

import java.util.List;

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a subarray whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 *
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 * Constraints:
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 *
 */
public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;

        int res = minSubArrayLen(target, nums);
        System.out.println("Result = " + res);
    }

    /**
     * Using Sliding Window
     *
     * @param target
     * @param nums
     * @return
     */
    private static int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int start = 0;
        int minLength = Integer.MAX_VALUE;

        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];

            while (sum >= target) {
                minLength = Math.min(minLength, end - start + 1);

                sum -= nums[start];
                start++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * Using Binary Search with Prefix Sum
     *
     * @param target
     * @param nums
     * @return
     */
    private static int minSubArrayLen_1st(int target, int[] nums) {


        return 0;
    }
}
