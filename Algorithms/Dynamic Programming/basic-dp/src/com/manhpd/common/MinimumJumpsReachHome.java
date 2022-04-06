package com.manhpd.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 *
 * The bug jumps according to the following rules:
 *
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 *
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i],
 * and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home.
 * If there is no possible sequence of jumps that lands the bug on position x, return -1.
 *
 * Example 1:
 * Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * Output: 3
 * Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
 *
 * Example 2:
 * Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * Output: -1
 *
 * Example 3:
 * Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * Output: 2
 * Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.
 */
public class MinimumJumpsReachHome {

    private static int count = 0;

    public static void main(String[] args) {
//        int[] forbidden = {14, 4, 18, 1, 15};
//        int a = 3;
//        int b = 15;
//        int x = 9;

//        int[] forbidden = {8,3,16,6,12,20};
//        int a = 15;
//        int b = 13;
//        int x = 11;

//        int[] forbidden = {1,6,2,14,5,17,4};
//        int a = 16;
//        int b = 9;
//        int x = 7;

//        int[] forbidden = {18,13,3,9,8,14};
//        int a = 3;
//        int b = 8;
//        int x = 6;

        int[] forbidden = {162,118,178,152,167,100,40,74,199,186,26,73,200,127,30,124,193,84,184,36,103,149,153,9,54,154,133,95,45,198,79,157,64,122,59,71,48,177,82,35,14,176,16,108,111,6,168,31,134,164,136,72,98};
        int a = 29;
        int b = 98;
        int x = 80;

        MinimumJumpsReachHome solution = new MinimumJumpsReachHome();
        System.out.println(solution.minimumJumps(forbidden, a, b, x));
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        // using brute force approach
        Set<Integer> forbiddenSet = new HashSet<Integer>() {{
            Arrays.stream(forbidden).forEach(f -> add(f));
        }};

        int res = this.minimumJumpsRecursive(a, b, x, 0, forbiddenSet);
        count = 0;

        return res;
    }

    public int minimumJumpsRecursive(int a, int b, int destination, int origin, Set<Integer> forbiddenSet) {
        if (destination == origin) {
            return 0;
        }

        if (count >= 2) {
            return Integer.MIN_VALUE;
        }

        int num1 = Integer.MIN_VALUE;
        if (!forbiddenSet.contains(origin)) {
            if (origin < destination) {
                if (count > 0) {
                    count = 0;
                }
                num1 = minimumJumpsRecursive(a, b, destination, origin + a, forbiddenSet);
            } else { //if (origin > destination) {
                ++count;
                num1 = minimumJumpsRecursive(a, b, destination, origin - b, forbiddenSet);
            }
        } else {
            ++count;
            num1 = minimumJumpsRecursive(a, b, destination, origin - b, forbiddenSet);
        }

        return (num1 == Integer.MIN_VALUE || num1 == -1) ? -1 : num1 + 1;
    }

    public int minimumJumpsLoop(int[] forbidden, int a, int b, int destination) {
        return -1;
    }
}
