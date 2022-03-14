package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/B17yEjwOp0k
 *
 * We are given a sorted array of integers, nums, that contain n integers, where each integer is in the range [0,n−1] inclusive.
 * There is only one duplicate number in nums. Find and return this duplicate number.
 *
 * Constraints:
 *
 * 2 ≤ nums.length ≤ 10^3
 * 1 ≤ nums[i] < nums.length
 * All of the integers in nums appear only once, except for one integer that appears twice.
 *
 * Example 1:
 * Input: [1,2,3,4,4,5,6,7]
 * Output: 4
 *
 * Example 2:
 * Input: [1,1,2,3,4,5]
 * Output: 1
 *
 * Example 3:
 * Input: [1,2,3,4,5,5]
 * Output: 5
 *
 * Example 4:
 * Input: [1,1]
 * Output: 1
 *
 */
public class DuplicateElementSequentiallyNumbers {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 4, 5, 6};
//        int[] nums = {1, 2, 3, 4, 4, 5, 6, 7};
//        int[] nums = {1, 1, 2, 3, 4, 5};
//        int[] nums = {1, 2, 3, 4, 5, 5};
        int[] nums = {1, 1};
        int res = duplicateNumber(nums);
        System.out.println("Result: " + res);
    }

    static int duplicateNumber(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == nums[mid - 1]) {
                return mid;
            }

            if (nums[mid] > mid) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (nums[left] == left) {
            return left;
        }

        if (right < nums.length && nums[right] == right) {
            return right;
        }

        return -1;
    }

}
