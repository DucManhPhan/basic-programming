package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 */
public class Combinations {

    public static void main(String[] args) {
        int n = 4;
        int k = 2;

        List<List<Integer>> res = combine(n, k);
        print(res);
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combine(n, k, res, new ArrayList<>(), 1);

        return res;
    }

    public static void combine(int n, int k, List<List<Integer>> res, List<Integer> values, int idx) {
        if (values.size() == k) {
            res.add(new ArrayList<>(values));
            return;
        }

        for (int i = idx; i <= n; ++i) {
            values.add(i);
            combine(n , k, res, values, i + 1);
            values.remove(values.size() - 1);
        }
    }

    public static void print(List<List<Integer>> res) {
        res.stream().forEach(values -> {
            System.out.print("[");
            values.stream().forEach(value -> System.out.print(value + ", "));
            System.out.print("]");
            System.out.println();
        });
    }

}
