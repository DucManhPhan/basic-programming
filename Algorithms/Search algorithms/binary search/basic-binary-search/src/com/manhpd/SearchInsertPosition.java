package com.manhpd;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 *
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 *
 * Example 2:
 * Input: [1,3,5,6], 2
 * Output: 1
 *
 * Example 3:
 * Input: [1,3,5,6], 7
 * Output: 4
 *
 * Example 4:
 * Input: [1,3,5,6], 0
 * Output: 0
 *
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
//        int[] nums = {1, 3};
//        int target = 2;

//        int[] nums = {1, 3, 5, 6};
//        int target = 2;

        int[] nums = {2, 4, 5, 9, 11};
        int target = -1;

//        int res = searchInsert(nums, target);
        int res = searchInsertV2(nums, target);
        System.out.println(res);
    }

    /**
     * This solution followed the Binary Search Template #1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    /**
     * Followed the Binary Search Template #3.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsertV2(int[] nums, int target) {
        int blue = -1;
        int red = nums.length;

        while (blue + 1 != red) {
            int mid = blue + (red - blue) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                blue = mid;
            } else {
                red = mid;
            }
        }

        return red;
    }

}
