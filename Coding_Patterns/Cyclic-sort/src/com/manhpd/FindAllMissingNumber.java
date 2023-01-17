package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given an unsorted array containing numbers taken from the range 1 to ‘n’.
 * The array can have duplicates, which means some numbers will be missing. Find all those missing numbers.
 *
 * Example 1:
 * Input: [2, 3, 1, 8, 2, 3, 5, 1]
 * Output: 4, 6, 7
 * Explanation: The array should have all numbers from 1 to 8, due to duplicates 4, 6, and 7 are missing.
 *
 * Example 2:
 * Input: [2, 4, 1, 2]
 * Output: 3
 *
 * Example 3:
 * Input: [2, 3, 2, 1]
 * Output: 4
 *
 */
public class FindAllMissingNumber {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 8, 2, 3, 5, 1};
        List<Integer> missingNumbers = findNumbers(nums);

        System.out.println(missingNumbers.toString());
    }

    public static List<Integer> findNumbers(int[] nums) {
        int size = nums.length;
        int start = 0;

        while (start < size) {
            int current = nums[start] - 1;

            if (nums[start] != nums[current]) {
                swap(nums, start, current);
            } else {
                ++start;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();
        for (int i = 0; i < size; ++i) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i + 1);
            }
        }

        return missingNumbers;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
