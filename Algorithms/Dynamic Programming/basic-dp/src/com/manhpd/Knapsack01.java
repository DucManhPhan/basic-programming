package com.manhpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two integer arrays to represent weights and profits of ‘N’ items,
 * we need to find a subset of these items which will give us maximum profit
 * such that their cumulative weight is not more than a given number ‘C’.
 * Each item can only be selected once, which means either we put an item in the knapsack or we skip it.
 *
 * Ex1: Items: { Apple, Orange, Banana, Melon }
 *      Weights: { 2, 3, 1, 4 }
 *      Profits: { 4, 5, 3, 7 }
 *      Knapsack capacity: 5
 *
 */
public class Knapsack01 {

    private static int maxProfit = 0;

    private static List<Integer> result;

    private static List<List<Integer>> realResults = new ArrayList<>();

    public static void main(String[] args) {
        int[] weights = {2, 3, 1, 4};
        int[] profits = {4, 5, 3, 7};
        int capacity = 5;

//        long startMs = System.currentTimeMillis();
//        bruteForce(weights, profits, capacity);
//        long duration = System.currentTimeMillis() - startMs;
//
//        System.out.println("Time process of brute force: " + duration + " ms.");
//        result.stream().forEach(System.out::println);
//        System.out.println(realResults.toString());
    }

    /**
     * Use backtracking paradigm to scan all the length of set of number
     *
     * @param weights
     * @param profits
     * @param capacity
     * @return
     */
    public static void bruteForce(int[] weights, int[] profits, int capacity) {
        int len = weights.length;

        for (int count = 1; count <= len; ++count) {
            getWeights(weights, profits, count, capacity);
        }
    }

    private static void getWeights(int[] weights, int[] profits, int length, int capacity) {
        List<Integer> tmpResult = new ArrayList<>();
        getWeights(weights, profits, capacity, tmpResult, length, 0);
    }

    private static void getWeights(int[] weights, int[] profits, int capacity,
                                   List<Integer> tmpResult, int currentLength,
                                   int currentIndex) {
        if (tmpResult.size() == currentLength) {
            int currentWeight = tmpResult.stream().mapToInt(index -> weights[index]).sum();
            realResults.add(new ArrayList<>(tmpResult));

            if (currentWeight <= capacity) {
                int currentProfit = tmpResult.stream().mapToInt(index -> profits[index]).sum();
                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                    result = new ArrayList<>(tmpResult);
//                    realResults.add(new ArrayList<>(tmpResult));
                }
            }

            return;
        }

        for (int i = currentIndex; i < weights.length; ++i) {
            tmpResult.add(i);
            getWeights(weights, profits, capacity, tmpResult, currentLength, i + 1);
            tmpResult.remove(tmpResult.size() - 1);
        }
    }



}
