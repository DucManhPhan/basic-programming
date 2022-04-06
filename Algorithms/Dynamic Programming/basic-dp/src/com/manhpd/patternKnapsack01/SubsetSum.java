package com.manhpd.patternKnapsack01;

/**
 * Given a set of positive numbers, determine if there exists a subset whose sum is equal to a given number ‘S’.
 *
 * Example 1:
 * Input: {1, 2, 3, 7}, S=6
 * Output: True
 * The given set has a subset whose sum is '6': {1, 2, 3}
 *
 * Example 2:
 * Input: {1, 2, 7, 1, 5}, S=10
 * Output: True
 * The given set has a subset whose sum is '10': {1, 2, 7}
 *
 * Example 3:
 * Input: {1, 3, 4, 8}, S=6
 * Output: False
 * The given set does not have any subset whose sum is equal to '6'.
 *
 */
public class SubsetSum {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 7};
//        int S = 6;

        int[] nums = {1, 2, 7, 1, 5};
        int S = 10;

//        int[] nums = {1, 3, 4, 8};
//        int S = 6;

        SubsetSum solution = new SubsetSum();
        System.out.println(solution.findSubsetSum(nums, S));
    }

    /**
     * Use recursion to iterate all cases
     *
     * @param nums
     * @param S
     * @return
     */
    private boolean findSubsetSum(int[] nums, int S) {
        return isEqualSubsetSum(nums, S, 0);
    }

    private boolean isEqualSubsetSum(int[] nums, int S, int idx) {
        if (idx >= nums.length) {
            return false;
        }

        if (S == 0) {
            return true;
        }

        boolean isFirstSum = false;
        if (nums[idx] <= S) {
            isFirstSum = isEqualSubsetSum(nums, S - nums[idx], ++idx);
        }

        boolean isSecondSum = isEqualSubsetSum(nums, S, ++idx);
        return isSecondSum || isFirstSum;
    }

}
