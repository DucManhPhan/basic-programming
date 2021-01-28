package com.manhpd;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/permutation-coefficient/
 *
 * P(n, k) = P(n-1, k) + k * P(n-1, k-1)
 *
 */
public class PermutationCoefficient {

    public static void main(String[] args) {
//        int n = 10;
//        int k = 2;

        int n = 10;
        int k = 9;

//        int n = 10;
//        int k = 0;

//        int n = 10;
//        int k = 1;

//        int n = 10;
//        int k = 3;

        PermutationCoefficient solution = new PermutationCoefficient();

        // 1st way
//        long startMs = System.currentTimeMillis();
//        int res = solution.calcPermutations(n, k);
//        System.out.println(res);
//        System.out.println(System.currentTimeMillis() - startMs);

        // 2nd way
//        long startMs = System.currentTimeMillis();
//        long res = solution.dpTopDown(n, k);
//        System.out.println(res);
//        System.out.println(System.currentTimeMillis() - startMs);

        // 3rd way
        long res = solution.dpBottomUp(n, k);
        System.out.println(res);
    }

    /**
     * Using recursive version to load all cases
     * @param n
     * @param k
     * @return
     */
    private int calcPermutations(int n, int k) {
        if (k == 0) {
            return 1;
        }

        if (k == 1) {
            return n;
        }

        if (n < k) {
            return 0;
        }

        return calcPermutations(n - 1, k) + (k * calcPermutations(n - 1, k - 1));
    }

    /**
     * Using top-down way of DP
     *
     * @param n
     * @param k
     */
    private long dpTopDown(int n, int k) {
        long[][] res = new long[n + 1][k + 1];

        // set the default value for all elements of res array
        for (long[] tmp : res) {
            Arrays.fill(tmp, -1);
        }

        // set base case
        for (int i = 0; i <= n; ++i) {
            res[i][0] = 1;
            res[i][1] = i;

            for (int j = 0; j <= k; ++j) {
                if (i < j) {
                    res[i][j] = 0;
                }
            }
        }

        System.out.println(res);
        return permuatationTopDown(res, n, k);
    }

    private long permuatationTopDown(long[][] res, int n, int k) {
        if (k == 0 || k == 1 || res[n][k] != -1) {
            return res[n][k];
        }

        res[n][k] = permuatationTopDown(res, n - 1, k) + (k * permuatationTopDown(res, n - 1, k - 1));
        return res[n][k];
    }

    /**
     * Using bottom-up way of DP
     *
     * @param n
     * @param k
     * @return
     */
    private long dpBottomUp(int n, int k) {
        long res[][] = new long[n + 1][k + 1];

        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                if (j == 0) {
                    res[i][j] = 1;
                } else if (j == 1) {
                    res[i][j] = i;
                } else if (i < j) {
                    res[i][j] = 0;
                } else {
                    res[i][j] = res[i - 1][j] + (j * res[i - 1][j - 1]);
                }
            }
        }

        return res[n][k];
    }

    /**
     * Using one dimensional array for DP
     *
     * @param n
     * @param k
     * @return
     */
    private long dpBottomUpOneDimensional(int n, int k) {
        long res[] = new long[n + 1];
        res[0] = 1;

        for (int i = 1; i <= n; ++i) {
            res[i] = i * res[i - 1];
        }

        return res[n] / res[n - k];
    }

}
