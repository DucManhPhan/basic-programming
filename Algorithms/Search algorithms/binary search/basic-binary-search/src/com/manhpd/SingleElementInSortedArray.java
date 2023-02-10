package com.manhpd;

/**
 * Suppose we are given a sorted array that only consists of integers, where every element appears twice,
 * except for one element which appears once. Find the element that appears only once.
 *
 * Constraints:
 * - `1 ≤ nums.length ≤ 10^3`.
 * - `0 ≤ nums[i] ≤ 10^3`.
 *
 * Example 1:
 * - Input: `[1,1,2,3,3,4,4,8,8]`
 * - Output: 2
 *
 * Example 2:
 * - Input: `[3,3,7,7,10,11,11]`
 * - Output: 10
 *
 * Example 3:
 * - Input: `[1,1,2,2,3]`
 * - Output: 3
 *
 * Example 4:
 * - Input: `[1]`
 * - Output: 1
 */
public class SingleElementInSortedArray {

    public static void main(String[] args) {
//        int[] nums = {1,1,2,3,3,4,4,8,8};
//        int[] nums = {3,3,7,7,10,11,11};
//        int[] nums = {1,1,2,2,3};
        int[] nums = {1};

        int res = singleNonDuplicate(nums);
        System.out.println("Result: " + res);
    }

    /**
     * Using bitwise operator.
     *
     * Time complexity: O(n)
     *
     * @param nums
     * @return
     */
    public static int singleNonDuplicate(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            res ^= nums[i];
        }

        return res;
    }

    /**
     * Using binary search's third invariant
     *
     * @param nums
     * @return
     */
    public static int singleNonDuplicateV1(int[] nums) {


        return -1;
    }


}
