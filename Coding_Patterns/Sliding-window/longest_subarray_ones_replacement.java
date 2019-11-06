package com.manhpd;

import java.util.HashMap;
import java.util.Map;

public class longest_subarray_ones_replacement {

    public static void main(String[] args) {

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
        }

        return 0;
    }

}
