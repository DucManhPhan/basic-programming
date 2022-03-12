package com.manhpd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/B8R83jyN3KY
 *
 * Given a set of distinct numbers, find all of its permutations.
 * Permutation is defined as the re-arranging of the elements of the set. For example, {1, 2, 3} has the following six permutations:
 * {1, 2, 3}
 * {1, 3, 2}
 * {2, 1, 3}
 * {2, 3, 1}
 * {3, 1, 2}
 * {3, 2, 1}
 *
 * If a set has ‘n’ distinct elements it will have n! permutations.
 *
 * Example 1:
 * Input: [1,3,5]
 * Output: [1,3,5], [1,5,3], [3,1,5], [3,5,1], [5,1,3], [5,3,1]
 *
 */
public class Permutations {

    public static void main(String[] args) {
//        List<List<Integer>> result = Permutations.findPermutations(new int[] { 1, 3, 5 });
        List<List<Integer>> result = Permutations.findPermutationsWithRecursive(new int[] { 1, 3, 5 });
        System.out.print("Here are all the permutations: " + result);
    }

    /**
     * Using BFS algorithm
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<List<Integer>> permutations = new LinkedList<>();
        permutations.add(new ArrayList<>());

        for (int currentNumber : nums) {
            int n = permutations.size();

            for (int i = 0; i < n; ++i) {
                List<Integer> oldPermutation = permutations.poll();
                for (int j = 0; j <= oldPermutation.size(); ++j) {
                    List<Integer> newPermutation = new ArrayList<>(oldPermutation);
                    newPermutation.add(j, currentNumber);

                    if (newPermutation.size() == nums.length) {
                        result.add(newPermutation);
                    } else {
                        permutations.add(newPermutation);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Using recursive algorithm
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> findPermutationsWithRecursive(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        findPermutationsWithRecursive(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private static void findPermutationsWithRecursive(int[] nums, int index, List<Integer> currentPermutation,
                                                     List<List<Integer>> result) {
        if (currentPermutation.size() == nums.length) {
            result.add(currentPermutation);
            return;
        }

        for (int i = 0; i <= currentPermutation.size(); ++i) {
            List<Integer> newPermutation = new ArrayList<>(currentPermutation);
            newPermutation.add(i, nums[index]);

            findPermutationsWithRecursive(nums, index + 1, newPermutation, result);
        }
    }

}
