package com.manhpd;

/**
 * Given a number n, the task is to find the XOR from 1 to n.
 *
 * Ex1: Input : n = 6
 * Output : 7
 * // 1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6  = 7
 *
 * Ex2: Input : n = 7
 * Output : 0
 * // 1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7 = 0
 *
 */
public class ComputeXOR1toN {

    public static void main(String[] args) {
        int n = 6;
        int res = computeXORFrom1ToN(n);
        System.out.println(" 1 to " + n + ": " + res);

        n = 7;
        res = computeXORFrom1ToN(n);
        System.out.println(" 1 to " + n + ": " + res);

        n = 8;
        res = computeXORFrom1ToN(n);
        System.out.println(" 1 to " + n + ": " + res);

        n = 9;
        res = computeXORFrom1ToN(n);
        System.out.println(" 1 to " + n + ": " + res);

        n = 10;
        res = computeXORFrom1ToN(n);
        System.out.println(" 1 to " + n + ": " + res);

        n = 11;
        res = computeXORFrom1ToN(n);
        System.out.println(" 1 to " + n + ": " + res);

        n = 13;
        res = computeXORFrom1ToN(n);
        System.out.println(" 1 to " + n + ": " + res);

        n = 121;
        res = computeXORFrom1ToN(n);
        System.out.println(" 1 to " + n + ": " + res);
    }

    /**
     * Time complexity: O(n)
     *
     * @param n
     * @return
     */
    public static int computeXORFrom1ToN(int n) {
        int result = 0;
        for (int i = 1; i <= n; ++i) {
            result ^= i;
        }

        return result;
    }

}
