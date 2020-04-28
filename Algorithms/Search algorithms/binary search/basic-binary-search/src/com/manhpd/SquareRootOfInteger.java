package com.manhpd;

/**
 * Given an integer x, find it’s square root. If x is not a perfect square, then return floor(√x).
 *
 * Input: x = 4
 * Output: 2
 * Explanation:  The square root of 4 is 2.
 *
 * Input: x = 11
 * Output: 3
 * Explanation:  The square root of 11 lies in between
 * 3 and 4 so floor of the square root is 3.
 *
 */
public class SquareRootOfInteger {

    public static void main(String[] args) {
//        int x = 11;
//        int x = 4;
//        int x = 2147395600;
        int x = 2147395599;
//        int res = mySqrt(x);
        int res = mySqrtBinarySearch(x);
        System.out.println(res);
    }

    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int i = 1;
        int res = 1;

        while (res <= x) {
            ++i;
            res = i * i;
        }

        return i - 1;
    }

    /**
     * The Binary Search can be further optimized to start with ‘start’ = 0 and ‘end’ = x/2.
     * Floor of square root of x cannot be more than x/2 when x > 1.
     *
     * @param x
     * @return
     */
    public static int mySqrtBinarySearch(int x) {
        if (x < 2) {
            return x;
        }

        int left = 2;
        int right = x / 2;
        int pos = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long num = (long)mid * mid;

            if (num == x) {
                return mid;
            }

            if (x < num) {
                right = mid - 1;
            } else {
                left = mid + 1;
                pos = mid;
            }
        }

        return pos;
    }

}
