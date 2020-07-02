package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = { 2, 3, 5};
        int target = 8;

//        List<List<Integer>> res = combinationSum(candidates, target);
        List<List<Integer>> res = combinationSumUnboundedKnapsack(candidates, target);
        Combinations.print(res);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(candidates, target, 0, res, new ArrayList<>(), 0);

        return res;
    }

    public static void combinationSum(int[] candidates, int target, int sum, List<List<Integer>> res, List<Integer> values, int idx) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            if (!res.contains(values)) {
                res.add(new ArrayList<>(values));
            }

            return;
        }

        for (int i = idx; i < candidates.length; ++i) {
            values.add(candidates[i]);
            combinationSum(candidates, target, sum + candidates[i], res, values, i);
            values.remove(values.size() - 1);
        }
    }

    /**
     * Use unbounded Knapsack version
     *
     * @param weight
     * @param target
     * @param i
     * @param j
     * @param res
     * @param list
     */
    public static void solve(int[] weight, int target, int i, int j, List<List<Integer>> res, List<Integer> list){
        if(i >= j || target < 0) return;
        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }

        list.add(weight[i]);
        solve(weight,target - weight[i], i, j, res, list); //include item

        list.remove(list.size() - 1);
        solve(weight, target,i + 1, j, res, list); //dont include item
    }

    public static List<List<Integer>> combinationSumUnboundedKnapsack(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        solve(candidates, target,0,candidates.length, res, new ArrayList<>());

        return res;
    }

}
