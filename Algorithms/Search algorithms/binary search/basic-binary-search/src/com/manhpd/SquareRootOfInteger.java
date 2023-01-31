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
//        int x = 2147395599;
        int x = 2147483647;
//        int res = mySqrt(x);
//        int res = mySqrtV1(x);
        int res = mySqrtV2(x);
//        int res = mySqrtBinarySearch(x);
        System.out.println(res);
    }

    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int i = 1;
        long res = 1;

        while (res <= x) {
            ++i;
            res = (long) i * i;
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

    private static int mySqrtV1(int x) {
        int left = 0;
        int right = x/2;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            }

            if (x < square) {
                right = mid;
            } else {
                left = mid;
            }
        }


        if (((long) right * right) <= x) {
            return right;
        }

        if (((long) left * left) <= x) {
            return left;
        }

        return -1;
    }

    private static int mySqrtV2(int a) {
        if (a < 2) {
            return a;
        }
        // the initial value for left is 0
        int left = 0;
        // the initial value for right is the number
        int right = a;
        // left + 1 >= right will finish while loop
        while (left + 1 < right) {
            int mid = (right + left) / 2;
            long square = (long) mid * mid;

            if (square == a) {
                // mid is the answer
                return mid;
            } else if (square < a) {
                // there is no sense to search among numbers less than mid
                left = mid;
            } else {
                // there is no sense to search among numbers bigger than mid
                right = mid;
            }
        }
        // the answer is left
        return left;
    }

}
