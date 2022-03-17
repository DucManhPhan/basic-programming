package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/N0JQ0XxZ5Z6
 * <p>
 * We’ll call an array, nums, a bitonic array if the following properties hold:
 * <p>
 * nums.length \geq 3
 * nums.length≥3
 * There exists some i with 0 < i < nums.length - 1 such that:
 * - nums[0] < nums[1] < ... < nums[i-1] < nums[i]
 * - nums[i] > nums[i+1] > ... > nums[nums.length - 1]
 * <p>
 * Suppose we are given an integer array nums that is guaranteed to be a bitonic one, and return any i such that nums[0] < nums[1] < ... < nums[i - 1] < nums[i] > nums[i + 1] > ... > nums[nums.length - 1].
 * <p>
 * Constraints:
 * + 3 <= nums.length <= 10^4
 * + 0 <= nums[i] <= 10^6
 * + nums is guaranteed to be a bitonic array.
 * <p>
 * Example 1:
 * Input: [0,1,0]
 * Output: 1
 * <p>
 * Example 2:
 * Input: [0,2,1,0]
 * Output: 1
 * <p>
 * Example 3:
 * Input: [0,10,5,2]
 * Output: 1
 * <p>
 * Example 4:
 * Input: [3,4,5,1]
 * Output: 2
 * <p>
 * Example 5:
 * Input: [24,69,100,99,79,78,67,36,26,19]
 * Output: 2
 */
public class BitonicPointArray {

    public static void main(String[] args) {
//        int[] nums = {0, 1, 0};
//        int[] nums = {0, 2, 1, 0};
//        int[] nums = {0, 10, 5, 2};
//        int[] nums = {3, 4, 5, 1};
        int[] nums = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};

        int result = findBitonicPoint(nums);
        System.out.println("Result: " + result);
    }

    public static int findBitonicPoint(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (mid > 0 && mid < nums.length - 1) {
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
