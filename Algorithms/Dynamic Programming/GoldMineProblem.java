package com.manhpd;


import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/gold-mine-problem/
 *
 */
public class GoldMineProblem {

    private static int M = 4;   // row

    private static int N = 4;   // column

    private static int MIN_GOLD = -1;

    public static void main(String[] args) {
        init();
    }

    private static void init() {
//        int[][] golds = {
//                {1, 3, 3},
//                {2, 1, 4},
//                {0, 6, 4}
//        };
        int[][] golds = {{10, 33, 13, 15},
                {22, 21, 04, 1},
                {5, 0, 2, 3},
                {0, 6, 14, 2}};

        int[][] maxGolds = new int[M][N];
        Arrays.stream(maxGolds).forEach(a -> Arrays.fill(a, MIN_GOLD));

        int[][] positions = new int[M][N];
        Arrays.stream(positions).forEach(a -> Arrays.fill(a, 0));

        for (int i = 0; i < M; ++i) {
            maxGolds[i][0] = golds[i][0];
        }

        for (int i = 0; i < M; ++i) {
            mineGold(golds, maxGolds, positions, i, N - 1);
        }

//        printResult(maxGolds);
        // find cell that contains max number of gold and its index
        int maxGold = maxGolds[0][N - 1];
        int maxGoldIndex = 0;
        for (int j = 1; j < M; ++j) {
            if (maxGold < maxGolds[j][N - 1]) {
                maxGold = maxGolds[j][N - 1];
                maxGoldIndex = j;
            }
        }
    }

    private static int mineGold(int[][] golds, int[][] maxGolds, int[][] positions, int row, int column) {
        if (maxGolds[row][column] == MIN_GOLD) {
            for (int i = row - 1; i <= row + 1; ++i) {
                if (i >= 0 && i < M) {
                    int gold = mineGold(golds, maxGolds, positions, i, column - 1);
                    if (maxGolds[row][column] < gold + golds[row][column]) {
                        maxGolds[row][column] = gold + golds[row][column];
                        positions[row][column] = i;
                    }
                }
            }
        }

        return maxGolds[row][column];
    }

    private static void printResult(int[][] maxGolds) {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                System.out.println("gold[" + i + "][" + j + "] = " + maxGolds[i][j]);
            }
        }
    }

}
