package com.manhpd.java;

import java.util.HashMap;
import java.util.Map;

/**
 * You are visiting a farm to collect fruits. The farm has a single row of fruit trees.
 * You will be given two baskets, and your goal is to pick as many fruits as possible to be placed in the given baskets.
 *
 * You will be given an array of characters where each character represents a fruit tree. The farm has following restrictions:
 * - Each basket can have only one type of fruit. There is no limit to how many fruit a basket can hold.
 * - You can start with any tree, but you can’t skip a tree once you have started.
 * - You will pick exactly one fruit from every tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
 *
 * Write a function to return the maximum number of fruits in both baskets.
 *
 * Example 1:
 *
 * Input: Fruit=['A', 'B', 'C', 'A', 'C']
 * Output: 3
 * Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']
 *
 * Example 2:
 *
 * Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
 * Output: 5
 * Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
 * This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']
 *
 */
public class FruitsIntoBaskets {

    public static void main(String[] args) {
        // Example 1
        char[] fruits = {'A', 'B', 'C', 'A', 'C'};
        int expected = 3;

        // Example 2
//        char[] fruits = {'A', 'B', 'C', 'B', 'B', 'C'};
//        int expected = 5;

        int result = findLength(fruits);
        System.out.printf("Result: %d, Expected: %d", result, expected);
    }

    public static int findLength(char[] arr) {
        int windowStart = 0;
        int maxFruits = 1;
        Map<Character, Integer> numberFruitsPerBasket = new HashMap<>();

        for (int windowEnd = 0; windowEnd < arr.length; ++windowEnd) {
            char cEnd = arr[windowEnd];
            numberFruitsPerBasket.put(cEnd, numberFruitsPerBasket.getOrDefault(cEnd, 0) + 1);

            int numberOfBaskets = numberFruitsPerBasket.size();
            while (numberOfBaskets > 2) {
                char cStart = arr[windowStart];
                numberFruitsPerBasket.put(cStart, numberFruitsPerBasket.get(cStart) - 1);

                if (numberFruitsPerBasket.get(cStart) == 0) {
                    numberFruitsPerBasket.remove(cStart);
                }

                ++windowStart;
                numberOfBaskets = numberFruitsPerBasket.size();
            }

            maxFruits = Math.max(maxFruits, windowEnd - windowStart + 1);
        }

        return maxFruits;
    }
}
