package com.manhpd;

/**
 * Suppose we are given a sorted array of distinct integers, nums, which are sorted in ascending order.
 * We will return the smallest index i, which satisfies nums[i] == i. If no such i exists, we will return -1.
 * <p>
 * Constraints:
 * - 1 ≤ nums.length ≤ 10
 * - -10^3 ≤ nums[i] ≤ 10
 * <p>
 * Example 1:
 * Input: [-10,-5,0,3,7]
 * Output: 3
 * <p>
 * Example 2:
 * Input: [0,2,5,8,17]
 * Output: 0
 * <p>
 * Example 3:
 * Input: [-10,-5,3,4,7,9]
 * Output: -1
 */
public class FirstElementEqualItsIndex {

    public static void main(String[] args) {
//        int[] nums = {-10, -5, 0, 3, 7};
//        int[] nums = {0, 2, 5, 8, 17};
        int[] nums = {-10, -5, 3, 4, 7, 9};

        int res = fixedPoint(nums);
        System.out.println("Result: " + res);
    }

    public static int fixedPoint(int[] nums) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == mid && left < nums[left]) {
                return mid;
            }

            if (nums[mid] < mid) {
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
