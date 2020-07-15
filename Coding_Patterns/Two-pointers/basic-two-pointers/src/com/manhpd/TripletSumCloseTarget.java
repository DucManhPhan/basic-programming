package com.manhpd;

import java.util.Arrays;

/**
 * Given an array of unsorted numbers and a target number, find a triplet in the array whose sum is as close to the target number as possible, return the sum of the triplet.
 * If there are more than one such triplet, return the sum of the triplet with the smallest sum.
 *
 * Example 1:
 *
 * Input: [-2, 0, 1, 2], target=2
 * Output: 1
 * Explanation: The triplet [-2, 1, 2] has the closest sum to the target.
 * Example 2:
 *
 * Input: [-3, -1, 1, 2], target=1
 * Output: 0
 * Explanation: The triplet [-3, 1, 2] has the closest sum to the target.
 * Example 3:
 *
 * Input: [1, 0, 1, 1], target=100
 * Output: 3
 * Explanation: The triplet [1, 1, 1] has the closest sum to the target.
 *
 */
public class TripletSumCloseTarget {

    private static int smallestSum = Integer.MAX_VALUE;

    public static void main(String[] args) {
//        int[] arr = {-2, 0, 1, 2};
//        int targetSum = 2;

        int[] arr = {-3, -1, 1, 2};
        int targetSum = 1;

//        int[] arr = {1, 2, 4, 8, 16, 32, 64, 128};
//        int targetSum = 82;

//        int[] arr = {1, 0, 1, 1};
//        int targetSum = 100;

        int res = searchTriplet(arr, targetSum);
        System.out.println(res);
    }

    /**
     * Using backtracking algorithm to iterate all cases
     *
     * @param arr
     * @param targetSum
     * @return
     */
    public static int searchTriplet0(int[] arr, int targetSum) {


        return -1;
    }

    public static void searchTriplet0(int[] arr, int targetSum, int sum, int num) {
        if (num == 3) {
            smallestSum = Math.min(smallestSum, sum);
            return;
        }

        for (int i = 0; i < arr.length; ++i) {

        }
    }

    /**
     * Using two pointers technique
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchTriplet(int[] nums, int target) {
        int minDistance = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i <= nums.length - 3; ++i) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int diff = target - nums[i] - nums[left] - nums[right];
                if (diff == 0) {
                    return target - diff;
                }

                if (Math.abs(diff) < Math.abs(minDistance)
                                    || (Math.abs(diff) == Math.abs(minDistance) && diff > minDistance)) {
                    minDistance = diff;
                }

                if (diff > 0) {
                    ++left;
                } else {
                    --right;
                }
            }
        }

        return target - minDistance;
    }

}
