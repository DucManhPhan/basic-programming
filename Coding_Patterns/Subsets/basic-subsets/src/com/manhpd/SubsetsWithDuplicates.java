package com.manhpd;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/7npk3V3JQNr
 *
 * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
 *
 * Example 1:
 * Input: [1, 3, 3]
 * Output: [], [1], [3], [1,3], [3,3], [1,3,3]
 *
 * Example 2:
 * Input: [1, 5, 3, 3]
 * Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3], [3,3], [1,3,3], [3,3,5], [1,5,3,3]
 *
 */
public class SubsetsWithDuplicates {

    public static void main(String[] args) {
        List<List<Integer>> result = SubsetsWithDuplicates.findSubsets(new int[] { 1, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetsWithDuplicates.findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }

    /**
     * Using BFS algorithm
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList());
        Arrays.sort(nums);

        int startIdx = 0;
        int endIdx = 0;
        for (int j = 0; j <nums.length; ++j) {
            startIdx = 0;

            if (j > 0 && nums[j] == nums[j - 1]) {
                startIdx = endIdx + 1;
            }

            endIdx = subsets.size() - 1;
            int num = nums[j];

            for (int i = startIdx; i <= endIdx; ++i) {
                List<Integer> newSubset = new ArrayList<>(subsets.get(i));
                newSubset.add(num);

                subsets.add(newSubset);
            }
        }

        return subsets;
    }

    /**
     * Using Backtracking algorithm
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsetsWithBacktracking(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();

        return subsets;
    }

}
