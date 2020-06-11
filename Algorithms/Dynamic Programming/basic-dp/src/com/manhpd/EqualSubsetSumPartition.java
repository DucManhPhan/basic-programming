package com.manhpd;

import java.util.Arrays;

/**
 * Given a set of positive numbers, find if we can partition it into two subsets such that the sum of elements in both the subsets is equal.
 *
 * Input: {1, 2, 3, 4}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 4} & {2, 3}
 *
 * Input: {1, 1, 3, 4, 7}
 * Output: True
 * Explanation: The given set can be partitioned into two subsets with equal sum: {1, 3, 4} & {1, 7}
 *
 * Input: {2, 3, 4, 6}
 * Output: False
 * Explanation: The given set cannot be partitioned into two subsets with equal sum.
 *
 */
public class EqualSubsetSumPartition {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        boolean canPartition = canPartition(nums);

        System.out.println(canPartition);
    }

    public static boolean canPartition(int[] num) {
        if (num == null || num.length == 0) {
            return false;
        }

        int sum = Arrays.stream(num).sum();
        if ((sum & 1) != 0) {
            return false;
        }

        boolean canPartition = canPartition(num, 0, sum/2);
        return canPartition;
    }

    public static boolean canPartition(int[] num, int currentIndex, int target) {
        if (currentIndex >= num.length || target < 0) {
            return false;
        }

        if (target == 0) {
            return true;
        }

        boolean isAdded = false;
        boolean isSkipped = false;
        if (num[currentIndex] <= target) {
            isAdded = canPartition(num, currentIndex + 1, target - num[currentIndex]);
        }

        isSkipped = canPartition(num, currentIndex + 1, target);
        return isAdded || isSkipped;
    }

}
