package com.manhpd.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array containing 0s and 1s,
 * if you are allowed to replace no more than ‘k’ 0s with 1s,
 * find the length of the longest contiguous subarray having all 1s.
 *
 * Input: Array=[0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1], k=2
 * Output: 6
 * Explanation: Replace the '0' at index 5 and 8 to have the longest contiguous subarray of 1s having length 6.
 *
 * Input: Array=[0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1], k=3
 * Output: 9
 * Explanation: Replace the '0' at index 6, 9, and 10 to have the longest contiguous subarray of 1s having length 9.
 */
public class LongestSubarrayOnesReplacement {

    public static void main(String[] args) {
        // Example 1
//        int[] a = {0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1};
//        int k = 2;
//        int expected = 6;

        // Example 2
        int[] a = {0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1};
        int k = 3;
        int expected = 9;

        System.out.printf("Result: %d, Expected: %d", findLength(a, k), expected);
    }

    private static int findLength(int[] arr, int k) {
        int windowStart = 0;
        int numZeros = 0;
        int maxLength = Integer.MIN_VALUE;

        for (int windowEnd = 0; windowEnd < arr.length; ++windowEnd) {
            int digitEnd = arr[windowEnd];
            numZeros = digitEnd == 0 ? numZeros + 1 : numZeros;

            while (numZeros > k) {
                int digitStart = arr[windowStart];
                numZeros = digitStart == 0 ? numZeros - 1 : numZeros;

                ++windowStart;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    private static int getLongestLength(int[] chars, int k) {
        int windowStart = 0;
        int longestLength = 0;
        int maxSameLetters = 0;
        Map<Integer, Integer> charFrequencyMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < chars.length; ++windowEnd) {
            int right = chars[windowEnd];
            charFrequencyMap.put(right, charFrequencyMap.getOrDefault(right, 0) + 1);
            maxSameLetters = Math.max(maxSameLetters, charFrequencyMap.get(right));

            while (windowEnd - windowStart + 1 - maxSameLetters > k) {
                int left = chars[windowStart];
                charFrequencyMap.put(left, charFrequencyMap.get(left) - 1);
                ++windowStart;
            }

            longestLength = Math.max(longestLength, windowEnd - windowStart + 1);
        }


        return longestLength;
    }

    private static int getLongestLength_Short(int[] a, int k) {
        int windowStart = 0;
        int maxOnesCount = 0;
        int longestLength = 0;

        for (int windowEnd = 0; windowEnd < a.length; ++windowEnd) {
            if (a[windowEnd] == 1) {
                ++maxOnesCount;
            }

            if (windowEnd - windowStart + 1 - maxOnesCount > k) {
                if (a[windowStart] == 1) {
                    --maxOnesCount;
                }

                ++windowStart;
            }

            longestLength = Math.max(longestLength, windowEnd - windowStart + 1);
        }

        return longestLength;
    }
}
