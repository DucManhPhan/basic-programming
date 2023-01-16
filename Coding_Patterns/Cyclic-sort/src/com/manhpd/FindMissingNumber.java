package com.manhpd;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * We are given an array containing n distinct numbers taken from the range 0 to n.
 * Since the array has only n numbers out of the total n+1 numbers, find the missing number.
 *
 * Example 1:
 * Input: [4, 0, 3, 1]
 * Output: 2
 *
 * Example 2:
 * Input: [8, 3, 5, 2, 4, 6, 0, 1]
 * Output: 7
 *
 */
public class FindMissingNumber {

    /**
     * 1st way: Using Cyclic Sort
     *
     * @param nums
     * @return
     */
    public static int findMissingNumber(int[] nums) {
        int startIdx = 0;

        while (startIdx < nums.length) {
            int correctIdx = nums[startIdx];

            if (nums[startIdx] != nums[correctIdx]) {
                swap(nums, startIdx, correctIdx);
            } else {
                ++startIdx;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return -1;
    }

    private static void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

    /**
     * 2nd way: Using brute force
     *
     * @param nums
     * @return
     */
    public static int findMissingNumberV1(int[] nums) {
        int size = nums.length;

        // bubble sort
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size - i - 1; ++j) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }

        // find the first item's value and its index is different
        for (int i = 0; i < size; ++i) {
            if (i != nums[i]) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 3rd way: Using HashSet to contain a range from [0, n]
     *
     * @param nums
     * @return
     */
    public static int findMissingNumberV2(int[] nums) {
        int size = nums.length;

        Set<Integer> fullNumbers = new HashSet<>();
        IntStream.range(0, size)
                 .forEach(i -> fullNumbers.add(nums[i]));

        for (int i = 0; i < size; ++i) {
            if (!fullNumbers.contains(i)) {
                return i;
            }
        }

        return size;
    }

    public static void main(String[] args) {
        int[] nums = {4, 0, 3, 1};
//        int missing = findMissingNumber(nums);
        int missing = findMissingNumberV1(nums);

        System.out.println("Missing number: " + missing);
    }

}
