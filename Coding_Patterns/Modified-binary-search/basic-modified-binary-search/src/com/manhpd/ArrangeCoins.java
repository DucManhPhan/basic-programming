package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/RLrAvNRWkRK
 *
 * Suppose that there are a total of n coins that we want to form into a staircase shape, where every k-th row contains exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 *
 * Constraints:
 * 1 ≤ n ≤ 10^3
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: Because the 3rd row is incomplete, we will return 2.
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 *
 * Example 2:
 * Input: 8
 * Output: 3
 * Explanation: Because the 4th row is incomplete, we will return 3.
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 *
 */
public class ArrangeCoins {

    public static void main(String[] args) {
//        int n = 5;
        int n = 8;
        int res = ArrangeCoins.arrangeCoinsV2(n);
        System.out.println("Result: " + res);
    }

    /**
     * Create an sorted array for applying Binary Search algorithm
     *
     * @param n
     * @return
     */
    public static int arrangeCoins(int n) {
        // build the staircase coins
        List<Integer> staircase = new ArrayList<>();
        int coin = 1;
        int remainedCoins = n;

        while (remainedCoins > 0) {
            if (remainedCoins < coin) {
                coin = remainedCoins;
            }

            staircase.add(coin);
            remainedCoins = remainedCoins - coin;

            ++coin;
        }

        // using binary search tree to iterate
        int left = 0;
        int right = staircase.size();

        while (left + 1 < right) {
            int mid = left + (right - left)/2;

            if (mid > 0 && staircase.get(mid) > staircase.get(mid - 1)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (right < staircase.size() && staircase.get(right) <= staircase.get(right - 1)) {
            return right;
        }

        return staircase.size();
    }

    /**
     * Without creating a new array
     *
     * @param n
     * @return
     */
    public static int arrangeCoinsV2(int n) {
        if (n < 2) {
            return n;
        }

        int left = 0;
        int right = n;

        while (left + 1 < right) {
            int mid = left + (right - left)/2;

            if (mid * (mid + 1) / 2 == n) {
                return mid;
            } else if (n > mid * (mid + 1) / 2) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
