package com.manhpd.patternFibonacciNumber;

/**
 * Given an array of positive numbers, where each element represents the max number of jumps that can be made forward from that element,
 * write a program to find the minimum number of jumps needed to reach the end of the array (starting from the first element).
 * If an element is 0, then we cannot move through that element.
 *
 * Example 1:
 *
 * Input = {2,1,1,1,4}
 * Output = 3
 * Explanation: Starting from index '0', we can reach the last index through: 0->2->3->4
 * Example 2:
 *
 * Input = {1,1,3,6,9,3,0,1,3}
 * Output = 4
 * Explanation: Starting from index '0', we can reach the last index through: 0->1->2->3->8
 */
public class MinimumJumpsReachEnd {

    public static void main(String[] args) {
        int[] jumps = {2, 1, 1, 1, 4};
        int steps = countMinJumps(jumps);

        System.out.println("Result: " + steps);
    }

    public static int countMinJumps(int[] jumps) {
        return countMinJumps(jumps, 0);
    }

    public static int countMinJumps(int[] jumps, int idx) {
        // base case
        if (idx == jumps.length - 1) {
            return 0;
        }

        if (jumps[idx] == 0) {
            return Integer.MAX_VALUE;
        }

        // recursion case
        int totalJumps = Integer.MAX_VALUE;
        int start = idx + 1;
        int end = idx + jumps[idx];

        while (start < jumps.length && start <= end) {
            int minJumps = countMinJumps(jumps, start++);

            if (minJumps != Integer.MAX_VALUE) {
                totalJumps = Math.min(totalJumps, minJumps + 1);
            }
        }

        return totalJumps;
    }

}
