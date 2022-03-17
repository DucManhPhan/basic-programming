package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/7n1yXVWZ3jy
 *
 * Suppose we are given an integer array, nums, which is guaranteed to be a bitonic array and a target value.
 * It will return the index if the target is found. If not, it will return -1.
 *
 * Constraints:
 * - 3 <= nums.length <= 10^5
 * - 1 <= nums[i], target <= 10^8
 * - A given array always contains a bitonic point.
 * - Array nums always contain distinct elements.
 *
 * Example 1:
 * Input: [3, 9, 10, 20, 17, 5, 1], 20
 * Output: 3
 *
 * Example 2:
 * Input: [5, 6, 7, 8, 9, 10, 3, 2, 1], 30
 * Output: -1
 *
 */
public class ElementInBitonicArray {

    public static void main(String[] args) {
//        int[] nums = {3, 9, 10, 20, 17, 5, 1};
//        int target = 20;

        int[] nums = {5, 6, 7, 8, 9, 10, 3, 2, 1};
        int target = 30;

        int res = findIndexOfTarget(nums, target);
        System.out.println("Result: " + res);
    }

    public static int findIndexOfTarget(int[] nums, int target) {
        int bitoniPoint = findBitonicPoint(nums);
        int idxTargetLeftSide = findIndexOfTarget(nums, target, bitoniPoint, true);
        if (idxTargetLeftSide != -1) {
            return idxTargetLeftSide;
        }

        int idxTargetRightSide = findIndexOfTarget(nums, target, bitoniPoint, false);
        if (idxTargetRightSide != -1) {
            return idxTargetRightSide;
        }

        return -1;
    }

    public static int findIndexOfTarget(int[] nums, int target,
                                        int bitonicPoint, boolean isIncreasedArray) {
        int left = 0;
        int right = bitonicPoint;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (isIncreasedArray) {
                if (nums[mid] < target) {
                    left = mid;
                } else {
                    right = mid;
                }
            } else {
                if (nums[mid] < target) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }

        if (left >= 0 && nums[left] == target) {
            return left;
        }

        if (right >= 0 && nums[right] == target) {
            return right;
        }

        return -1;
    }

    public static int findBitonicPoint(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (mid > 0 && mid < nums.length) {
                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                    return mid;
                }
            }

            if (mid < nums.length && nums[mid] < nums[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
