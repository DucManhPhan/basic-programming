package com.manhpd;


import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.
 * Write a function to return the indices of the two numbers (i.e. the pair) such that they add up to the given target.
 *
 * Ex1: Input: [1, 2, 3, 4, 6], target=6
 * Output: [1, 3]
 * Explanation: The numbers at index 1 and 3 add up to 6: 2+4=6
 *
 * Ex2: Input: [2, 5, 9, 11], target=11
 * Output: [0, 2]
 * Explanation: The numbers at index 0 and 2 add up to 11: 2+9=11
 */
public class PairWithTarget {

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 4, 6};
//        int targetSum = 6;

        int[] arr = {2, 5, 9, 11};
        int targetSum = 11;

        int[] result = search(arr, targetSum);
        Arrays.stream(result).forEach(System.out::println);
    }

    private static int[] search(int[] arr, int targetSum) {
        int size = arr.length;
        int start = 0;
        int end = size - 1;

        while (start < size || end > 0) {
            if (arr[start] + arr[end] > targetSum) {
                --end;
                continue;
            }

            if (arr[start] + arr[end] < targetSum) {
                ++start;
                continue;
            }

            return new int[]{start, end};
        }

        return new int[]{-1, -1};
    }

    public static int[] searchStandard(int[] arr, int targetSum) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (currentSum == targetSum)
                return new int[] { left, right }; // found the pair

            if (targetSum > currentSum)
                left++; // we need a pair with a bigger sum
            else
                right--; // we need a pair with a smaller sum
        }

        return new int[] { -1, -1 };
    }

}
