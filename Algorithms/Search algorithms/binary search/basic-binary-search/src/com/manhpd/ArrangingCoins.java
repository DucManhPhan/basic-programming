package com.manhpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/arranging-coins/description/
 * Suppose that there are a total of n coins that we want to form into a staircase shape, where every k-th row contains exactly k coins.
 * Given `n`, find the total number of full staircase rows that can be formed.
 *
 * Constraints:
 * - `1 ≤ n ≤ 10^3`
 *
 *  Example 1:
 *  - Input: 5
 *  - Output: 2
 *
 *  Example 2:
 *  - Input: 8
 *  - Output: 3
 *
 */
public class ArrangingCoins {

    public static void main(String[] args) {
//        int n = 1;
//        int n = 2;
//        int n = 3;
//        int n = 5;
//        int n = 8;
        int n = 1804289383; // 60070

//        int res = arrangeCoins(n);
//        int res = arrangeCoinsV1(n);
        int res = arrangeCoinsV2(n);
        System.out.println("Result: " + res);
    }

    /**
     * Using brute force algorithm
     *
     * @param n
     * @return
     */
    private static int arrangeCoins(int n) {
        if (n == 1) {
            return n;
        }

        int coin = 1;
        int remaining = n;

        while (remaining >= 0) {
            if (remaining < coin) {
                return --coin;
            }

            remaining -= coin;
            ++coin;
        }

        return -1;
    }

    /**
     * Define stair cases array. Then, use Binary Search to work on this array
     *
     * @param n
     * @return
     */
    private static int arrangeCoinsV1(int n) {
        List<Integer> stairCase = buildStairCase(n);
        System.out.println(Arrays.toString(stairCase.toArray()));

        int left = 0;
        int right = stairCase.size();

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (stairCase.get(mid - 1) < stairCase.get(mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (left == 0 || stairCase.get(left - 1) < stairCase.get(left)) {
            return left + 1;
        }

        return stairCase.size();
    }

    private static List<Integer> buildStairCase(int n) {
        List<Integer> stairCase = new ArrayList<>();

        int coin = 1;
        int remaining = n;

        while (remaining > 0) {
            if (remaining < coin) {
                coin = remaining;
            }

            remaining -= coin;
            stairCase.add(coin);
            ++coin;
        }

        return stairCase;
    }

    /**
     * Using binary search with only number n. Do not create stair case array
     *
     * @param n
     * @return
     */
    private static int arrangeCoinsV2(int n) {
        int left = 0;
        int right = n;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            long numCoins =  ((long)(mid + 1) * mid) / 2;

            if (numCoins == n) {
                return mid;
            }

            if (numCoins < n) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
