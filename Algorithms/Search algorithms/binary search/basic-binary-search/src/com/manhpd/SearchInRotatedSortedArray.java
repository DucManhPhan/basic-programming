package com.manhpd;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        int[] nums = {7, 0, 1, 2, 4, 5, 6};
        int[] nums = {5, 6, 7, 0, 1, 2, 4};
        int target = 0;

//        int[] nums = {1, 3};
//        int target = 1;

//        int[] nums = {5, 1, 3};
//        int target = 3;

//        int pos = search(nums, target);
//        int pos = searchV1(nums, target);
        int pos = searchV3(nums, target);
        System.out.println(pos);
    }

    /**
     * Using Binary search's the first invariant.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] <= nums[right]) { // right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {        // left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }

    /**
     * Using Binary Search's third invariant
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchV1(int[] nums, int target) {
        int left = 0;

        // The initially condition of right variable is not correct when using the template of Binary Search's third invariant.
        // But in this problem, we need to use it to pass all test case in LeetCode.
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] > nums[left]) {
                if (nums[left] <= target && target <= nums[mid]) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
                if (right < nums.length && nums[mid] <= target && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid;
                }
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

    /**
     * Using Using pivot element to search on each sub-arrays.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchV3(int[] nums, int target) {
        int pivot = findPivot(nums);

        int res = binarySearch(nums, 0, pivot, target);
        if (res != -1) {
            return res;
        }

        return binarySearch(nums, pivot, nums.length, target);
    }

    private static int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid - 1] > nums[mid]) {
                return mid;
            } else if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return nums[left] > nums[0] ? 0 : left;
    }

    private static int binarySearch(int[] nums, int startIdx, int endIdx, int target) {
        int left = startIdx;
        int right = endIdx;

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

        if (right < nums.length - 1 && nums[right] == target) {
            return right;
        }

        return -1;
    }

}
