package com.manhpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;

        List<List<Integer>> res = combinationSum2(candidates, target);
        Combinations.print(res);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2(candidates, target, res, new ArrayList<>(), 0);

        return res;
    }

    public static void combinationSum2(int[] candidates, int target, List<List<Integer>> res, List<Integer> values, int idx) {
        if (target == 0) {
            res.add(new ArrayList<>(values));
            return;
        }

        for (int i = idx; i < candidates.length; ++i) {
            if (idx != i && candidates[i] == candidates[i - 1]) {
                continue;
            }

            values.add(candidates[i]);
            combinationSum2(candidates, target - candidates[i], res, values, i + 1);
            values.remove(values.size() - 1);
        }
    }

}
