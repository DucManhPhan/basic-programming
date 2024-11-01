package com.manhpd.java;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring, which has all distinct characters.
 *
 * Example 1:
 *
 * Input: String="aabccbb"
 * Output: 3
 * Explanation: The longest substring with distinct characters is "abc".
 *
 * Example 2:
 *
 * Input: String="abbbb"
 * Output: 2
 * Explanation: The longest substring with distinct characters is "ab".
 *
 * Example 3:
 *
 * Input: String="abccde"
 * Output: 3
 * Explanation: Longest substrings with distinct characters are "abc" & "cde".
 *
 */
public class LongestSubstringWithDistinctCharacters {

    public static void main(String[] args) {
        // Example 1
//        String str = "aabccbb";
//        int expected = 3;

        // Example 2
//        String str = "abbbb";
//        int expected = 2;

        // Example 3
        String str = "abccde";
        int expected = 3;

        int result = findLength(str);
        System.out.printf("Result: %d, Expected: %d", result, expected);
    }

    public static int findLength(String str) {
        int windowStart = 0;
        int maxLength = Integer.MIN_VALUE;

        Map<Character, Integer> characterFrequency = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            char cEnd = str.charAt(windowEnd);

            while (characterFrequency.containsKey(cEnd)) {
                char cStart = str.charAt(windowStart);
                characterFrequency.put(cStart, characterFrequency.get(cStart) - 1);

                if (characterFrequency.get(cStart) == 0) {
                    characterFrequency.remove(cStart);
                }

                ++windowStart;
            }

            characterFrequency.put(cEnd, characterFrequency.getOrDefault(cEnd, 0) + 1);
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }
}
