package com.manhpd;

public class PermutationCoefficient {

    public static void main(String[] args) {
        System.out.println(getPermutationCoefficient(10, 2));
    }

    private static int getPermutationCoefficient_Recursive(int n, int k) {
        if (k == 0) {
            return 1;
        }

        if (k == 1) {
            return n;
        }

        if (k > n) {
            return 0;
        }

        return getPermutationCoefficient_Recursive(n - 1, k) +
               k * getPermutationCoefficient_Recursive(n - 1, k - 1);
    }

    // Use tabulation method
    private static int getPermutationCoefficient(int n, int k) {
        int[][] P = new int[n + 2][k + 2];
        int i, j;

        for (i = 0; i <= n; ++i) {
            for (j = 0; j <= Math.min(i, k); ++j) {
                if (j == 0) {
                    P[i][j] = 1;
                } else {
                    P[i][j] = P[i - 1][j] + j * P[i - 1][j - 1];
                }

                P[i][j + 1] = 0;
            }
        }

        return P[n][k];
    }

}
