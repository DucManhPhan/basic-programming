package com.manhpd.java;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string and a pattern, find the smallest substring in the given string which has all the character occurrences of the given pattern.
 *
 * Example 1:
 *
 * Input: String="aabdec", Pattern="abc"
 * Output: "abdec"
 * Explanation: The smallest substring having all characters of the pattern is "abdec"
 *
 * Example 2:
 *
 * Input: String="aabdec", Pattern="abac"
 * Output: "aabdec"
 * Explanation: The smallest substring having all character occurrences of the pattern is "aabdec"
 *
 * Example 3:
 *
 * Input: String="abdbca", Pattern="abc"
 * Output: "bca"
 * Explanation: The smallest substring having all characters of the pattern is "bca".
 *
 * Example 4:
 *
 * Input: String="adcad", Pattern="abc"
 * Output: ""
 * Explanation: No substring in the given string has all characters of the pattern.
 *
 */
public class SmallestWindowContainingSubstring {
    public static void main(String[] args) {
        // Example 1
        String s = "aabdec";
        String t = "abc";
        String expected = "abdec";

        // Example 1
//        String s = "aabdec";
//        String t = "abac";
//        String expected = "aabdec";

        // Example 1
//        String s = "abdbca";
//        String t = "abc";
//        String expected = "bca";

        // Example 1
//        String s = "adcad";
//        String t = "abc";
//        String expected = "";

        String result = findSubstring(s, t);
        System.out.printf("Result: %s, Expected: %s", result, expected);
    }

    private static String findSubstring(String s, String t) {
        int windowStart = 0;
        Map<Character, Integer> charFrequency = new HashMap<>();
        int minLength = Integer.MAX_VALUE;
        int matched = 0;
        String result = "";

        for (char c : t.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < s.length(); ++windowEnd) {
            char cEnd = s.charAt(windowEnd);

            if (charFrequency.containsKey(cEnd)) {
                charFrequency.put(cEnd, charFrequency.get(cEnd) - 1);

                if (charFrequency.get(cEnd) == 0) {
                    ++matched;
                }

                if (charFrequency.get(cEnd) < 0) {
                    char cStart = s.charAt(windowStart++);

                    if (charFrequency.containsKey(cStart)) {
                        if (charFrequency.get(cStart) == 0) {
                            --matched;
                        }

                        charFrequency.put(cEnd, charFrequency.get(cEnd) + 1);
                    }
                }
            }

            if (matched == charFrequency.size()) {
                if (minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1;

                    result = s.substring(windowStart, windowEnd + 1);
                }

                ++windowStart;
            }
        }

        return result;
    }
}
