package com.manhpd.patternKnapsack01;

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

        // use brute force way
        long startMs = System.currentTimeMillis();
        bruteForce(weights, profits, capacity);
        long duration = System.currentTimeMillis() - startMs;

        System.out.println("Time process of brute force: " + duration + " ms.");
//        result.stream().forEach(System.out::println);
//        System.out.println(realResults.toString());

        // use recursive function
        startMs = System.currentTimeMillis();
        int res = solveKnapsack01(weights, profits, capacity);
        duration = System.currentTimeMillis() - startMs;

        System.out.println("Time process of recursive function: " + duration + " ms.");
        System.out.println(res);

        // use Top-Down dynamic programming
        startMs = System.currentTimeMillis();
        res = solveKnapsack01DP(weights, profits, capacity);
        duration = System.currentTimeMillis() - startMs;

        System.out.println("Time process of the top-down dp: " + duration + " ms.");
        System.out.println(res);
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

    public static int solveKnapsack01(int[] weights, int[] profits, int capacity) {
        return knapsackRecursive(weights, profits, capacity, 0);
    }

    private static int knapsackRecursive(int[] weights, int[] profits, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex >= weights.length) {
            return 0;
        }

        int profit1 = 0;
        if (weights[currentIndex] <= capacity) {
            profit1 = profits[currentIndex] + knapsackRecursive(weights, profits,
                                    capacity - weights[currentIndex], currentIndex + 1);
        }

        int profit2 = knapsackRecursive(weights, profits, capacity, currentIndex + 1);
        return Math.max(profit1, profit2);
    }

    public static int solveKnapsack01DP(int[] weights, int[] profits, int capacity) {
        int[][] dp = new int[profits.length][capacity + 1];

        // use for Top-Down Dynamic programming
//        return solveKnapsack01TopDown(dp, weights, profits, capacity, 0);

        // use for Bottom-Up Dynamic Programming
        return solveKnapsack01BottomUp(dp, weights, profits, capacity);
    }

    private static int solveKnapsack01TopDown(int[][] dp, int[] weights, int[] profits, int capacity, int currentIndex) {
        // base case
        if (capacity <= 0 || currentIndex >= weights.length) {
            return 0;
        }

        if (dp[currentIndex][capacity] != 0) {
            return dp[currentIndex][capacity];
        }

        int profit1 = 0;
        if (weights[currentIndex] <= capacity) {
            profit1 = profits[currentIndex] + solveKnapsack01TopDown(dp, weights, profits,
                    capacity - weights[currentIndex], currentIndex + 1);
        }

        int profit2 = solveKnapsack01TopDown(dp, weights, profits, capacity, currentIndex + 1);

        dp[currentIndex][capacity] = Math.max(profit1, profit2);
        return dp[currentIndex][capacity];
    }

    private static int solveKnapsack01BottomUp(int[][] dp, int[] weights, int[] profits, int capacity) {

        // initialize for our dp array
        for (int i = 0; i < weights.length; ++i) {
            dp[i][0] = 0;
        }

        for (int c = 0; c <= capacity; ++c) {
            if (weights[0] <= c) {
                dp[0][c] = profits[0];
            }
        }

        for (int i = 1; i < weights.length; ++i) {
            for (int c = 1; c < capacity + 1; ++c) {
                int profit1 = 0;
                int profit2 = 0;

                if (weights[i] <= c) {
                    profit1 = profits[i] + dp[i - 1][c - weights[i]];
                }

                profit2 = dp[i - 1][c];
                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        printSelectedElements(dp, weights, profits, capacity);
        return dp[weights.length - 1][capacity];
    }

    private static void printSelectedElements(int dp[][], int[] weights, int[] profits, int capacity) {
        System.out.print("Selected weights:");
        int totalProfit = dp[weights.length - 1][capacity];
        for (int i = weights.length - 1; i > 0; i--) {
            if (totalProfit != dp[i - 1][capacity]) {
                System.out.print(" " + weights[i]);
                capacity -= weights[i];
                totalProfit -= profits[i];
            }
        }

        if (totalProfit != 0)
            System.out.print(" " + weights[0]);
        System.out.println("");
    }

}
