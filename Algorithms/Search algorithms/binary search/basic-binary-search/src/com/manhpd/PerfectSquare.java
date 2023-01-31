package com.manhpd;

/**
 * Suppose we are given a positive integer a. Write a function that returns True if a is a perfect square and returns False if a is not a perfect square.
 *
 * Constraints:
 * 1 ≤ a ≤ 10^3
 *
 * Example 1:
 * Input: 16
 * Output: true
 *
 * Example 2:
 * Input: 14
 * Output: false
 *
 */
public class PerfectSquare {

    public static void main(String[] args) {
//        int a = 5;
        int a = 4;
        boolean res = isPerfectSquare(a);

        System.out.println("Result: " + res);
    }

    private static boolean isPerfectSquare(int a) {
        int left = 0;
        int right = a;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == a) {
                return true;
            }

            if (square < a) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return false;
    }

}
