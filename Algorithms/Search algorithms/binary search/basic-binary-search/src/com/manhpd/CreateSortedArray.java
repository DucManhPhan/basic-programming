package com.manhpd;

import java.util.stream.IntStream;

/**
 * Given an array, the task is to create a new sorted array in ascending order from the elements of the given array.
 *
 * Examples:
 *
 * Input : arr[] = {2, 5, 4, 9, 8}
 * Output : 2 4 5 8 9
 *
 * Input : arr[] = {10, 45, 98, 35, 45}
 * Output : 10 35 45 45 98
 *
 */
public class CreateSortedArray {

    public static void main(String[] args) {
        int[] nums = {2, 5, 4, 9, 8};
        int[] res = createSortedArray(nums);
        IntStream.of(res).forEach(System.out::println);
    }

    public static int[] createSortedArray(int[] nums) {

        return new int[]{};
    }

}
