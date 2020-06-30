package com.manhpd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Subset {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
//        List<List<Integer>> res = subset(nums);
        List<List<Integer>> res = subsetIII(nums);
        res.stream().forEach(values -> {
            System.out.print("[");
            values.stream().forEach(value -> System.out.print(value + ", "));
            System.out.print("]");
            System.out.println();
        });
    }

    public static List<List<Integer>> subset(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subset(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private static void subset(int[] nums, List<List<Integer>> res, List<Integer> values, int index) {
        res.add(new LinkedList<>(values));

        for (int i = index; i < nums.length; ++i) {
            values.add(nums[i]);
            subset(nums, res, values, i + 1);
            values.remove(values.size() - 1);
        }
    }

    public static List<List<Integer>> subsetII(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int sizeSub = 0; sizeSub <= nums.length; ++sizeSub) {
            subsetII(nums, res, new ArrayList<>(), 0, sizeSub);
        }

        return res;
    }

    public static void subsetII(int[] nums, List<List<Integer>> res, List<Integer> values, int index, int k) {
        if (values.size() == k) {
            res.add(new ArrayList<>(values));
            return;
        }

        for (int i = index; i < nums.length; ++i) {
            values.add(nums[i]);
            subsetII(nums, res, values, i + 1, k);
            values.remove(values.size() - 1);
        }
    }

    public static List<List<Integer>> subsetIII(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        for (int value : nums) {
            List<List<Integer>> subSets = new ArrayList<>(res);

            for (List<Integer> tmp : res) {
                subSets.add(new ArrayList<>(tmp){{add(value);}});
            }

            for (List<Integer> tmp : subSets) {
                res.add(tmp);
            }
        }

        return res;
    }

}
