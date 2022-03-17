package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/3wA6ROJEV9R
 *
 * Suppose we are given a sorted array of integers, find the number of occurrences of a given target value. If the target is not found in the array, return -1.
 *
 * Constraints:
 * + 1 <= nums.length <= 10^4
 * + -10^4 <= nums[i] <= 10^4
 * + nums contains values sorted in an ascending order
 * + -10^4 <= target <= 10^4
 *
 * Example 1:
 * Input: [5, 7, 7, 8, 8, 10], 8
 * Output: 2
 *
 */
public class ElementOccurrence {

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

//        int numOccurrence = numOccrenceOfTarget(nums, target);
        int numOccurrence = findCount(nums, target);
        System.out.println("Result: " + numOccurrence);
    }

    public static int numOccrenceOfTarget(int[] nums, int target) {
        int firstIdx = findFirstOccurrence(nums, target);
        if (firstIdx == -1) {
            return -1;
        }

        int lastIdx = findLastOccurrence(nums, target);
        return lastIdx - firstIdx + 1;
    }

    public static int findFirstOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (right >= 0 && nums[right] == target) {
            return right;
        }

        if (left < nums.length && nums[left] == target) {
            return left;
        }

        return -1;
    }

    public static int findLastOccurrence(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (right >= 0 && nums[right] == target) {
            return right;
        }

        if (left < nums.length && nums[left] == target) {
            return left;
        }

        return -1;
    }

    public static int findCount(int[] nums, int target) {
        int left = binarySearch(nums, target, true);

        if (left < 0) {
            return -1;
        }

        int right = binarySearch(nums, target, false);

        return right - left + 1;
    }

    /**
     * Combine the findFirstOccurrence() method and findLastOccurrence() method into one.
     *
     * @param nums
     * @param target
     * @param leftMost
     * @return
     */
    public static int binarySearch(int[] nums, int target, boolean leftMost) {
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
            } else if (nums[mid] < target) {
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
