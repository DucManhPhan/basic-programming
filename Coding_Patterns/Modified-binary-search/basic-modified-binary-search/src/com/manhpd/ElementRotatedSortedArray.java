package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/qVrPOGy1ox7
 * <p>
 * Suppose an array that was sorted in ascending order is rotated. For example, the array nums = [0,1,2,4,5,6,7] might become:
 * - [4,5,6,7,0,1,2], if it is rotated 4 times.
 * - [0,1,2,4,5,6,7], if it is rotated 7 times.
 * <p>
 * Notice that rotating an array [nums[0], nums[1], nums[2], ..., nums[n-1]] 1 time results in the array [nums[n-1], nums[0], nums[1], nums[2], ..., nums[n-2]].
 * Suppose we are given a sorted rotated array nums, and an integer target. If target is found in the array, we will return its index. Otherwise, we will return -1.
 * <p>
 * Constraints:
 * + 1 <= nums.length <= 5000
 * + -10^4 <= nums[i] <= 10^4
 * + All of values of nums are unique
 * + nums is guaranteed to be rotated at some pivot
 * + -10^4 <= target <= 10^4
 * <p>
 * Example 1:
 * Input: [4,5,6,7,0,1,2], 0
 * Output: 4
 * <p>
 * Example 2:
 * Input: [4,5,6,7,0,1,2], 3
 * Output: -1
 * <p>
 * Example 3:
 * Input: [1], 0
 * Output: -1
 */
public class ElementRotatedSortedArray {

    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        int target = 0;

        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 3;

//        int[] nums = {1};
//        int target = 0;

        int res = findTarget(nums, target);
        System.out.println("Result: " + res);
    }

    public static int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (mid > 0 && nums[mid - 1] > nums[mid]) {
                return mid;
            }

            if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static int binarySearch(int[] nums, int left, int right, int target) {
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

        if (nums[left] == target) {
            return left;
        }

        if (right < nums.length && nums[right] == target) {
            return right;
        }

        return -1;
    }

    public static int findTarget(int[] nums, int target) {
        int idxPivot = findPivot(nums);
        System.out.println("Index of pivot point: " + idxPivot);

        int leftHalfTarget = binarySearch(nums, 0, idxPivot - 1, target);
        if (leftHalfTarget != -1) {
            return leftHalfTarget;
        }

        int rightHalfTarget = binarySearch(nums, idxPivot, nums.length, target);
        if (rightHalfTarget != -1) {
            return rightHalfTarget;
        }

        return -1;
    }

}
