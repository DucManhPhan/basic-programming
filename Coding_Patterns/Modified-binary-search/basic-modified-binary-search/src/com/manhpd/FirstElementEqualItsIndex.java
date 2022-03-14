package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/q2pAr1p6ZZ0
 *
 * Suppose we are given a sorted array of distinct integers, nums, which are sorted in ascending order.
 * We will return the smallest index i, which satisfies nums[i] == i. If no such i exists, we will return -1.
 *
 * Constraints:
 * 1 ≤ nums.length ≤ 10
 * -10^3 <= nums[i] <= 10^3
 *
 * Example 1: General case
 * Input: [-10,-5,0,3,7]
 * Output: 3
 * Explanation: For the given array, nums[0] = -10, nums[1] = -5, nums[2] = 0, nums[3] = 3, the output is 3.
 *
 * Example 2: The answer is at the beginning of the array
 * Input: [0,2,5,8,17]
 * Output: 0
 * Explanation: nums[0] = 0. Thus the output is 0.
 *
 * Example 3: The answer is missing
 * Input: [-10,-5,3,4,7,9]
 * Output: -1
 * Explanation: There is no such i that nums[i] = i. Thus the output is -1.
 *
 */
public class FirstElementEqualItsIndex {

    public static void main(String[] args) {

    }

    public static int fixedPoint(int[] nums) {

        return -1;
    }

}
