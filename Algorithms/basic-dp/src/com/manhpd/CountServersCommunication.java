package com.manhpd;

import java.util.HashSet;
import java.util.Set;

public class CountServersCommunication {

    public static void main(String[] args) {
        int[][] grid = {
                {1,1,0,0},
                {0,0,1,0},
                {0,0,1,0},
                {0,0,0,1}
        };

        CountServersCommunication solution = new CountServersCommunication();
        System.out.println(solution.countServers(grid));
    }

    public int countServers(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;

        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        for (int i = 0; i < rowLen; ++i) {
            for (int j = 0; j < colLen; ++j) {
                if (grid[i][j] == 1) {
                    boolean canAddRow = rows.add(i);
                    boolean canAddCol = cols.add(j);

                    if (canAddCol && canAddRow) {

                    } else {

                    }
                }
            }
        }

        return -1;
    }

}
