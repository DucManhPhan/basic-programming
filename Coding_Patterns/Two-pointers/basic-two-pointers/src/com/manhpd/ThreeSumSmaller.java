package com.manhpd;

import java.util.Arrays;

/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * Example:
 *      Input: nums = [-2,0,1,3], and target = 2
 *      Output: 2
 *      Explanation: Because there are two triplets which sums are less than 2:
 *              [-2,0,1]
 *              [-2,0,3]
 *
 * Example 2:
 *      Input: nums = [3, 1, 0, -2], and target = 4
 *      Output: 3
 *
 * Follow up: Could you solve it in O(n2) runtime?
 *
 */
public class ThreeSumSmaller {

    public static void main(String[] args) {
//        int[] nums = {-2, 0, 1, 3};
//        int target = 2;

        int[] nums = {3, 1, 0, -2};
        int target = 4;

        int res = threeSumSmaller(nums, target);
        System.out.println(res);
    }

    /**
     * Using two pointers technique
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumSmaller(int[] nums, int target) {
        int countSmallerTriplets = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; ++i) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                if (currentSum < target) {
                    ++countSmallerTriplets;
                    --right;
                } else {
                    ++left;
                }
            }
        }

        return countSmallerTriplets;
    }

}
