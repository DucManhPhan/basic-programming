package com.manhpd;

/**
 * Suppose we are given an integer array, `nums`, which is guaranteed to be a bitonic array and a target value. It will return the index if the target is found. If not, it will return `-1`.
 *
 * Constraints:
 * - `3 ≤ nums.length ≤ 10^5`.
 * - `1 ≤ nums[i], target ≤ 10^8`.
 * - A given array always contains a bitonic point.
 * - Array `nums` always contain distinct elements.
 *
 * Example 1:
 * - Input: `[3, 9, 10, 20, 17, 5, 1], 20`
 * - Output: 3
 *
 * Example 1:
 * - Input: `[5, 6, 7, 8, 9, 10, 3, 2, 1], 30`
 * - Output: -1
 *
 */
public class SearchElementInBitonicArray {

    public static void main(String[] args) {
        int[] nums = {3, 9, 10, 20, 17, 5, 1};
//        int target = 20;    // res = 3
//        int target = 10;    // res = 2
        int target = 5;    // res = 5

//        int[] nums = {5, 6, 7, 8, 9, 10, 3, 2, 1};
//        int target = 30;

        int res = searchTarget(nums, target);
        System.out.println("Result: " + res);
    }

    /**
     * Searching a {target} in {nums} array - bitonic array.
     *
     * @param nums
     * @param target
     * @return
     */
    private static int searchTarget(int[] nums, int target) {
        int bitonicPoint = findBitonicPoint(nums);
        System.out.println("Bitonic point: " + bitonicPoint);

        int res = inUpwardTrend(nums, bitonicPoint, target);
        if (res != -1) {
            return res;
        }

        return inDownardTrend(nums, bitonicPoint, target);
    }

    private static int findBitonicPoint(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (mid > 0 && mid < nums.length - 1 && nums[mid] > nums[mid - 1]
                    && nums[mid] > nums[mid + 1]) {
                return mid;
            }

            if (mid > 0 && nums[mid] > nums[mid - 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int inUpwardTrend(int[] nums, int bitonicPoint, int target) {
        int left = 0;
        int right = bitonicPoint;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (nums[left] == target) {
            return left;
        }

        return -1;
    }

    private static int inDownardTrend(int[] nums, int bitonicPoint, int target) {
        int left = bitonicPoint;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (nums[left] == target) {
            return left;
        }

        return -1;
    }


}
