package com.manhpd;

/**
 *  Suppose we are given an array of integers, `nums`, sorted in a non-decreasing order, and a number, `target`.
 *  We will return `True` only if target is a majority element. If the target is not found in the array, return `False`.
 *  A majority element is an element that appears more than `N/2` times in an array of length `N`.
 *
 *     Constraints:
 *     - `1 ≤ nums.length ≤ 10^3`.
 *     - `−10^3 ≤ nums[i] ≤ 10^3`.
 *     - `1 ≤ target ≤ 10^3`.
 *
 *     Example 1:
 *     - Input: [2,4,5,5,5,5,5,6,6], 5
 *     - Output: true
 *     - Explanation: The value `5` appears `5` times and the length of the array is `9`. Thus, `5` is a majority element because `5 > 9/2` is true.
 *
 *     Example 2:
 *     - Input: [10,100,101,101], 101
 *     - Output: false
 *     - Explanation: `nums[0] = 0`. Thus, the output is `0`.
 *
 *     Example 3:
 *     - Input: [-10], 10
 *     - Output: false
 *     - Explanation: The target is not found in the array. Thus, the output is `false`.
 *
 */
public class MajorityElement {

    public static void main(String[] args) {
//        int[] nums = {2,4,5,5,5,5,5,6,6};
//        int target = 5;

//        int[] nums = {10,100,101,101};
//        int target = 101;

        int[] nums = {-10};
        int target = 10;

        boolean res = isMajorityElement(nums, target);
        System.out.println("Result: " + res);
    }

    private static boolean isMajorityElement(int[] nums, int target) {
        int firstOccurrence = findPosition(nums, target, true);
        int lastOccurrence = findPosition(nums, target, false);
        System.out.println("First occurrence: " + firstOccurrence + ", last occurrence: " + lastOccurrence);

        if (firstOccurrence == -1 || lastOccurrence == -1) {
            return false;
        }

        int num = lastOccurrence - firstOccurrence + 1;
        if (num > nums.length / 2) {
            return true;
        }

        return false;
    }

    private static int findPosition(int[] nums, int target, boolean leftMost) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                if (leftMost) {
                    right = mid;
                } else {
                    left = mid;
                }
                continue;
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

}
