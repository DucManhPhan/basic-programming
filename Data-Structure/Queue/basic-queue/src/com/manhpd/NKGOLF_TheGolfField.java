package com.manhpd;

/**
 * Refer: https://vn.spoj.com/problems/NKGOLF/
 *
 * Constraint:
 * 1 ≤ M, N ≤ 1000
 * |Hij| ≤ 109
 */
public class NKGOLF_TheGolfField {

    public static void main(String[] args) {
        int[][] goldField = {
                {9, 2, 4, 8},
                {3, 5, 7, 8},
                {6, 8, 1, 3}
        };

        int res = findMaximumGoldField(goldField);
        System.out.println(res);
    }

    public static int findMaximumGoldField(int[][] goldField) {
        if (goldField == null) {
            return -1;
        }

        int numRows = goldField.length;
        int numCols = goldField[0].length;

        Point startPoint = new Point(-1, -1);
        Point endPoint = new Point(- 1, -1);
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; j < numCols; ++j) {
                if (goldField[i][j] <= goldField[i][j + 1] && goldField[i][j] <= goldField[i + 1][j]) {
                    if (startPoint.row == -1 && startPoint.column == 1) {
                        startPoint.row = i;
                        startPoint.column = j;
                    } else {
                        endPoint.row = i;
                        endPoint.column = j;
                    }
                }
            }
        }

        return -1;
    }

}

class Point {

    public int row;

    public int column;

    public Point(int row, int col) {
        this.row = row;
        this.column = col;
    }
}