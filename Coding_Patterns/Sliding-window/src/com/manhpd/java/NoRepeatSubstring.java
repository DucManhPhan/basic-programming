package com.manhpd.java;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring which has no repeating characters.
 *
 * Input: String="aabccbb"
 * Output: 3
 * Explanation: The longest substring without any repeating characters is "abc".
 *
 * Input: String="abbbb"
 * Output: 2
 * Explanation: The longest substring without any repeating characters is "ab".
 *
 * Input: String="abccde"
 * Output: 3
 * Explanation: Longest substrings without any repeating characters are "abc" & "cde".
 *
 */
public class NoRepeatSubstring {

    public static void main(String[] args) {
        String str = "after calling";

        System.out.println("Longest length of string is: " + getLongestLength(str));
    }

    private static int getLongestLength(String str) {
        int windowStart = 0;
        int longestLength = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            char rightCharacter = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightCharacter)) {
                windowStart = Math.max(windowStart, charFrequencyMap.get(rightCharacter) + 1);
            }

            // save the last index of character
            // because we have other case: "acbcad"
            charFrequencyMap.put(rightCharacter, windowEnd);
            longestLength = Math.max(longestLength, windowEnd - windowStart + 1);
        }

        return longestLength;
    }
}
