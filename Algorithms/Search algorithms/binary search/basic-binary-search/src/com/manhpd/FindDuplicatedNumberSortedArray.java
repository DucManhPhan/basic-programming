package com.manhpd;

/**
 * We are given a sorted array of integers, `nums`, that contain `n` integers, where each integer is in the range `[0, n−1]` inclusive.
 * There is only one duplicate number in `nums`. Find and return this duplicate number.
 *
 * Constraints:
 *  - `2 <= nums.length <= 10^3`
 *  - `1 <= nums[i] < nums.length`
 *  - All of the integers in `nums` appear only once, except for one integer that appears twice.
 *
 * Example 1
 * - Input: `[1,2,3,4,4,5,6,7]`
 * - Output: 4
 *
 * Example 2
 * - Input: `[1,1,2,3,4,5]`
 * - Output: 1
 *
 * Example 3
 * - Input: `[1,2,3,4,5,5]`
 * - Output: 5
 *
 * Example 4
 * - Input: `[1,1]`
 * - Output: 1
 *
 */
public class FindDuplicatedNumberSortedArray {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 4, 5, 6, 7};
//        int[] nums = {1, 2, 3, 4, 5, 5};
//        int[] nums = {1, 1, 2, 3, 4, 5};
//        int[] nums = {1, 1};
//        int[] nums = {2, 3, 4, 5, 5};
        int[] nums = {2, 2, 3, 4, 5};
//        int res = getDuplicateNumber(nums);
        int res = getDuplicateNumberV1(nums);

        System.out.println("Result: " + res);
    }

    /**
     * Using sequential search
     *
     * @param nums
     * @return
     */
    public static int getDuplicateNumber(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }

        return -1;
    }

    public static int getDuplicateNumberV1(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == nums[mid - 1]) {
                return nums[mid];
            }

            if (nums[mid] > mid) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

}
