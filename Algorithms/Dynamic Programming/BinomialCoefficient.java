package com.manhpd;

public class BinomialCoefficient {

    public static void main(String[] args) {
        System.out.println(getBinomialCoefficient(5, 2));
    }

    private static int getBinomialCoefficient(int n, int k) {
        int[][] C = new int[n + 1][k + 1];
        int i, j;

        for (i = 0; i <= n; ++i) {
            for (j = 0; j <= min(i, k); ++j) {
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }

        return C[n][k];
    }

    private static int getBinomialCoefficient_Recursive(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        }

        return getBinomialCoefficient_Recursive(n - 1, k - 1) +
               getBinomialCoefficient_Recursive(n - 1, k);
    }

    private static int min(int a, int b) {
        return a > b ? b : a;
    }

}
