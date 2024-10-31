package com.manhpd.java;


/**
 * Input: [2, 1, 5, 2, 3, 2], S=7
 * Output: 2
 * Explanation: The smallest subarray with a sum great than or equal to '7' is [5, 2].
 *
 * Input: [2, 1, 5, 2, 8], S=7
 * Output: 1
 * Explanation: The smallest subarray with a sum greater than or equal to '7' is [8].
 *
 */
public class SmallestSubarrayGivenSum {

    public static void main(String[] args) {
        int[] a = {2, 1, 5, 2, 3, 2};
        int sum = 7;

        System.out.println("Size of smallest subarray is: " + getSmallestSubarray(a, sum));
    }

    private static int getSmallestSubarray(int[] a, int sum) {
        int windowStart = 0;
        int windowSum = 0;
        int size = a.length;
        int smallestSize = Integer.MAX_VALUE;

        for (int windowEnd = 0; windowEnd < size; ++windowEnd) {
            windowSum += a[windowEnd];

            while (windowSum >= sum) {
                smallestSize = Math.min(windowEnd - windowStart + 1, smallestSize);
                windowSum -= a[windowStart];
                ++windowStart;
            }
        }

        return smallestSize == Integer.MAX_VALUE ? 0 : smallestSize;
    }
}
