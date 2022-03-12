package com.manhpd;


import java.util.ArrayList;
import java.util.List;

/**
 * Refer: https://www.educative.io/courses/grokking-the-coding-interview/gx2OqlvEnWG
 *
 * Given a set with distinct elements, find all of its distinct subsets.
 *
 * Example 1:
 * Input: [1, 3]
 * Output: [], [1], [3], [1,3]
 *
 * Example 2:
 * Input: [1, 5, 3]
 * Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3]
 */
public class Subsets {

    public static void main(String[] args) {
        List<List<Integer>> result = findSubsetsWithBacktracking(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = findSubsetsWithBacktracking(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }

    /**
     * Using BFS pattern to produce subsets
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());

        for (int num : nums) {
            int lengthOfSubSets = subsets.size();
            for (int i = 0; i < lengthOfSubSets; ++i) {
                List<Integer> subset = new ArrayList<>(subsets.get(i));
                subset.add(num);

                subsets.add(subset);
            }
        }

        return subsets;
    }

    /**
     * Using backtracking algorithm
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsetsWithBacktracking(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        findSubsetsWithBacktracking(nums, 0, subsets, new ArrayList<>());

        return subsets;
    }

    public static void findSubsetsWithBacktracking(int[] nums, int currentIdx, List<List<Integer>> subsets, List<Integer> subset) {
        if (currentIdx <= nums.length) {
            List<Integer> newSubset = new ArrayList<>(subset);
            subsets.add(newSubset);
        }

        for (int i = currentIdx; i < nums.length; ++i) {
            int num = nums[i];
            subset.add(num);
            findSubsetsWithBacktracking(nums, i + 1, subsets, subset);
            subset.remove(subset.get(subset.size() - 1));
        }
    }

}
