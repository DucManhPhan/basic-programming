package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/B17yEjwOp0k
 *
 * We are given a sorted array of distinct integers and a target value.
 * We will return the index if the target is found. Otherwise, we will return the index to where it would be if it were inserted in order.
 *
 * Constraints:
 * 1 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * nums contains distinct values, sorted in ascending order.
 * -10^3 <= target <= 10^3
 *
 * Example 1: General case
 * Input: [2,4,5,9,11], 5
 * Output: 2
 *
 * Example 2: Missing element case
 * Input: [2,4,5,9,11], 3
 * Output: 1
 *
 * Example 3: Insert to the end of the array
 * Input: [2,4,5,9,11], 15
 * Output: 5
 *
 * Example 4: Insert to the beginning of the array
 * Input: [2,4,5,9,11], -1
 * Output: 0
 *
 */
public class InsertPosition {

    public static void main(String[] args) {
        int[] nums = {2, 4, 5, 9, 11};
        System.out.println("Result of general case: " + searchPosition(nums, 5));
        System.out.println("Result of missing case: " + searchPosition(nums, 3));
        System.out.println("Result of inserting end case: " + searchPosition(nums, 15));
        System.out.println("Result of inserting begin case: " + searchPosition(nums, -1));
    }

    public static int searchPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        if (target > nums[right - 1]) {
            return right;
        }

        if (target < nums[left]) {
            return left;
        }

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return right;
    }

}
