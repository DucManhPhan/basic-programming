package com.manhpd;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’.
 * The array has only one duplicate but it can be repeated multiple times.
 * Find that duplicate number without using any extra space. You are, however, allowed to modify the input array.
 *
 * Example 1:
 * Input: [1, 4, 4, 3, 2]
 * Output: 4
 *
 * Example 2:
 * Input: [2, 1, 3, 3, 5, 4]
 * Output: 3
 *
 * Example 3:
 * Input: [2, 4, 1, 4, 4]
 * Output: 4
 *
 */
public class FindDuplicateNumber {

    public static void main(String[] args) {
//        int[] nums = {1, 4, 4, 3, 2};
        int[] nums = {2, 1, 3, 3, 5, 4};
//        int[] nums = {2, 4, 1, 3, 4};
//        int duplicateNumber = findDuplicateNumber(nums);
//        int duplicateNumber = findDuplicateNumberV1(nums);
//        int duplicateNumber = findDuplicateNumberV2(nums);
        int duplicateNumber = findDuplicateNumberV3(nums);

        System.out.println(duplicateNumber);
    }

    /**
     * Using cyclic sort.
     * Then iterate each item in this array, get the difference between its value and its index
     *
     * @param nums
     * @return
     */
    public static int findDuplicateNumber(int[] nums) {
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

        for (int i = 0; i < size; ++i) {
            if (nums[i] != i + 1) {
                return nums[i];
            }
        }

        return size;
    }

    /**
     * The improved version of the above solution without iterating an aray again.
     *
     * @param nums
     * @return
     */
    public static int findDuplicateNumberV1(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int current = nums[i] - 1;
            if (nums[i] != i + 1) {
                if (nums[i] != nums[current])
                    swap(nums, i, current);
                else // we have found the duplicate
                    return nums[i];
            } else {
                i++;
            }
        }

        return -1;
    }

    /**
     * This way is wrong when we have nums = [2, 2, 2, 2, 2]
     *
     * @param nums
     * @return
     */
    public static int findDuplicateNumberV2(int[] nums) {
        int currentSum = Arrays.stream(nums).sum();
        int sufficientSum = IntStream.range(1, nums.length).sum();

        return currentSum - sufficientSum;
    }

    /**
     * Using flag to determine the duplication.
     *
     * @param nums
     * @return
     */
    public static int findDuplicateNumberV3(int[] nums) {
        int duplicate = -1;

        for (int i = 0; i < nums.length; ++i) {
            int val = (nums[i] < 0 ? -nums[i] : nums[i]);
            if (nums[val - 1] >= 0) {
                nums[val - 1] = -nums[val - 1];
            } else {
                duplicate = val;
                break;
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
            }
        }

        return duplicate;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
