package com.manhpd;

public class UnboundedKnapsack {

    public static void main(String[] args) {
        int[] profits = {15, 50, 60, 90};
        int[] weights = {1, 3, 4, 5};
//        int capacity = 8;
        int capacity = 6;

        UnboundedKnapsack solution = new UnboundedKnapsack();
//        System.out.println(solution.knapsackRecursive(profits, weights, capacity, 0));
        System.out.println(solution.knapsackUnboundedDP(profits, weights, capacity));
    }

    /**
     * Using brute force algorithm
     *
     * @param profits
     * @param weights
     * @param capacity
     * @param currentIndex
     * @return
     */
    public int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex >= weights.length) {
            return 0;
        }

        System.out.println("" + capacity + " - " + currentIndex);
        int currentWeight = weights[currentIndex];
        int maxProfit1 = 0;
        if (capacity >= currentWeight) {
            maxProfit1 = profits[currentIndex] + knapsackRecursive(profits, weights, capacity - currentWeight, currentIndex);
        }

        int maxProfit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);
        return Math.max(maxProfit1, maxProfit2);
    }

    public int knapsackUnboundedDP(int[] profits, int[] weights, int capacity) {
        // using top-down approach
//        int[][] res = new int[weights.length][capacity + 1];
//
//        int maxProfit = knapsackUnboundedTopDown(profits, weights, res, capacity, 0);
//        for (int i = 0; i < weights.length; ++i) {
//            System.out.println(Arrays.toString(res[i]));
//        }
//
//        return maxProfit;

        // using bottom-up approach
        return knapsackUnboundedBottomUp(profits, weights, capacity);
    }

    public int knapsackUnboundedTopDown(int[] profits, int[] weights, int[][] res, int capacity, int currentIdx) {
        if (capacity <= 0 || currentIdx >= weights.length) {
            return 0;
        }

        if (res[currentIdx][capacity] != 0) {
            return res[currentIdx][capacity];
        }

        int maxProfit1 = 0;
        if (weights[currentIdx] <= capacity) {
            maxProfit1 = profits[currentIdx] + knapsackUnboundedTopDown(profits, weights, res, capacity - weights[currentIdx], currentIdx);
        }

        int maxProfit2 = knapsackUnboundedTopDown(profits, weights, res, capacity, currentIdx + 1);
        res[currentIdx][capacity] = Math.max(maxProfit1, maxProfit2);
        return res[currentIdx][capacity];
    }

    public int knapsackUnboundedBottomUp(int[] profits, int[] weights, int capacity) {
        int[][] profitMt = new int[weights.length][capacity + 1];

//        for (int i = 0; i < weights.length; ++i) {
//            profitMt[i][0] = 0;
//        }

        for (int i = 0; i < weights.length; ++i) {
            for (int j = 1; j <= capacity; ++j) {
                int profit1 = 0;
                int profit2 = 0;

                if (weights[i] <= j) {
                    profit1 =  profitMt[i][j - weights[i]] + profits[i];
                }

                if (i > 0) {
                    profit2 = profitMt[i - 1][j];
                }

                profitMt[i][j] = Math.max(profit1, profit2);
            }
        }

        return profitMt[weights.length - 1][capacity];
    }
}
