package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/binary-search-coding-interview/RLrAvNRWkRK
 *
 * Suppose we are given an integer a.
 * Compute and return the square root of a. If a is not a perfect square, we will truncate the decimal digits.
 *
 * Constraints:
 * 0 <= a <= 10^3
 *
 * Example 1:
 * Input: 11
 * Output: 3
 * Explanation: The square root of 11 is 3.3166... and since the decimal part is truncated, 3 is returned.
 *
 * Example 2:
 * Input: 9
 * Output: 3
 * Explanation: 9 gives a perfect square root.
 *
 */
public class SquareRootOfInteger {

    public static void main(String[] args) {
//        int n = 11;
//        int n = 9;
//        int n = 28;
        int n = 50;

        int res = squareRootOf(n);
        System.out.println("Result: " + res);
    }

    public static int squareRootOf(int n) {
        int right = n / 2;
        int left = 0;

        while (left + 1 < right) {
            int mid = left + (right - left)/2;
            int squared = mid * mid;

            if (squared == n) {
                return mid;
            }

            if (mid * mid < n) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

}
