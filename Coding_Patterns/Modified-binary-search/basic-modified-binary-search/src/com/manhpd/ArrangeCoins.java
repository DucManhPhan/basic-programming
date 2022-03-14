package com.manhpd;

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
        int n = 5;
//        int n = 8
        int res = arrangeCoins(n);
        System.out.println("Result: " + res);
    }

    public static int arrangeCoins(int n) {

        return -1;
    }

}
