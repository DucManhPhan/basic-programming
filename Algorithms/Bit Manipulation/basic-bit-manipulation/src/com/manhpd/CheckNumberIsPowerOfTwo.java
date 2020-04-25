package com.manhpd;

/**
 * Given a positive integer, write a function to find if it is a power of two or not.
 *
 * Ex1: Input : n = 4
 * Output : Yes
 * 2^2 = 4
 *
 * Ex2: Input : n = 7
 * Output : No
 *
 * Ex3: Input : n = 32
 * Output : Yes
 * 2^5 = 32
 */
public class CheckNumberIsPowerOfTwo {

    public static void main(String[] args) {

    }

    /**
     * Using log() method of Math package
     *
     * Carefully, because its data type is double or float, so it can contains number error.
     * So, we do not use this way if possible.
     *
     * @param n
     * @return
     */
    private static boolean isPowerOfTwoUsingLogMath(long n) {
        if (n == 0) {
            return false;
        }

        long log2 = log2(n);
        return Math.ceil(log2) == Math.floor(log2);
    }

    public static long log2(long x) {
        return (long) (Math.log(x) / Math.log(2) + 1e-10);
    }

    /**
     * Convert our an interger to string. Then count the number of 1 bit.
     * If num == 1, then it is power of 2.
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param n
     * @return
     */
    private static boolean isPowerOfTwoUsingString(long n) {
        String bits = Long.toBinaryString(n);
        int count = 0;

        for (int i = 0; i < bits.length(); ++i) {
            if (bits.charAt(i) == '1') {
                ++count;
            }
        }

        if (count == 1) {
            return true;
        }

        return false;
    }

    /**
     * Iterative to divide n for 2.
     * Time complexity: O(log(n))
     * Space complexity: O(1)
     *
     * @param n
     * @return
     */
    private static boolean isPowerOfTwoUsingIterative(long n) {
        if (n == 0) {
            return false;
        }

        while (n != 1) {
            if (n % 2 != 0) {
                return false;
            }

            n = n / 2;
        }

        return true;
    }

    /**
     * If n is power of two, then n -1 that has all unset bits of n becomes set bits of n - 1, vice versa.
     * Time complexity: O(1)
     *
     * @param n
     * @return
     */
    private static boolean isPowerOfTwo(int n) {
        return n != 0 && ((n & (n - 1)) == 0);
    }

}
