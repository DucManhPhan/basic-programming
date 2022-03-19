package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/g7O91N41lwl
 * <p>
 * Suppose we are given a sorted array that only consists of integers,
 * where every element appears twice, except for one element which appears once.
 * Find the element that appears only once.
 * <p>
 * Constraints:
 * + 1<= nums.length <= 10^3
 * + 0 <= nums[i] <= 10^3
 * <p>
 * Example 1:
 * Input: [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * <p>
 * Example 2:
 * Input: [3,3,7,7,10,11,11]
 * Output: 10
 * <p>
 * Example 3:
 * Input: [1,1,2,2,3]
 * Output: 3
 * <p>
 * Example 4:
 * Input: [1]
 * Output: 1
 */
public class SingleElementSortedArray {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 3, 4, 4, 8, 8};
//        int[] nums = {3, 3, 7, 7, 10, 11, 11};
//        int[] nums = {1, 1, 2, 2, 3};
//        int[] nums = {1};

        int res = findSingleElement(nums);
        System.out.println("Result: " + res);
    }

    /**
     * 1st way: Using binary search algorithm
     *
     * @param nums
     * @return
     */
    public static int findSingleElement(int[] nums) {

        return -1;
    }

    /**
     * 2nd way: Using XOR operator
     *
     * @param nums
     * @return
     */
    public static int findSingleElementV2(int[] nums) {

        return -1;
    }

    /**
     * 3rd way: Mark the positive and negative for each element in an array
     * For example:
     * [1, -1, 2, -3, 3, -4, 4, -8, 8]
     *
     * Then, sum of all elements in an array
     *
     * @param nums
     * @return
     */
    public static int findSingleElementV3(int[] nums) {

        return -1;
    }


}
