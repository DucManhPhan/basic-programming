package com.manhpd;

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
        int n = 3;
//        int n = 5;
//        int n = 8;

        int res = arrangeCoins(n);
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


        return -1;
    }

    /**
     * Using binary search with only number n. Do not create stair case array
     *
     * @param n
     * @return
     */
    private static int arrangeCoinsV2(int n) {


        return -1;
    }

}
