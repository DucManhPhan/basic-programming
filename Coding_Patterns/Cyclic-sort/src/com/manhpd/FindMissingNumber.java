package com.manhpd;

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

    public static void main(String[] args) {
        int[] nums = {4, 0, 3, 1};
        int missing = findMissingNumber(nums);

        System.out.println("Missing number: " + missing);
    }

}
