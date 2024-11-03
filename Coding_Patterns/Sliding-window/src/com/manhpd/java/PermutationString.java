package com.manhpd.java;

import java.util.HashMap;
import java.util.Map;


/**
 * Given a string and a pattern, find out if the string contains any permutation of the pattern.
 *
 * Input: String="oidbcaf", Pattern="abc"
 * Output: true
 * Explanation: The string contains "bca" which is a permutation of the given pattern.
 *
 * Input: String="odicf", Pattern="dc"
 * Output: false
 * Explanation: No permutation of the pattern is present in the given string as a substring.
 *
 * Input: String="bcdxabcdy", Pattern="bcdyabcdx"
 * Output: true
 * Explanation: Both the string and the pattern are a permutation of each other.
 *
 * Input: String="aaacb", Pattern="abc"
 * Output: true
 * Explanation: The string contains "acb" which is a permutation of the given pattern.
 *
 */
public class PermutationString {

    public static void main(String[] args) {
        // Example 1
//        String target = "oidbcaf";
//        String pattern = "abc";
//        boolean expected = true;

        // Example 2
//        String target = "odicf";
//        String pattern = "dc";
//        boolean expected = false;

        // Example 3
//        String target = "bcdxabcdy";
//        String pattern = "bcdyabcdx";
//        boolean expected = true;

        // Example 4
        String target = "aaacb";
        String pattern = "abc";
        boolean expected = true;

//        System.out.printf("Result: %b, Expected: %b", findPermutation(target, pattern), expected);
        System.out.printf("Result: %b, Expected: %b", findPermutationV2(target, pattern), expected);
    }

    /**
     * This way didn't work with the case - duplicate characters like "aaacb"
     *
     * @param str
     * @param pattern
     * @return
     */
    private static boolean findPermutation(String str, String pattern) {
        int windowStart = 0;
        Map<Character, Integer> charFrequency = new HashMap<>();

        int numCharsInPattern = pattern.length();
        for (int i = 0; i < numCharsInPattern; ++i) {
            char c = pattern.charAt(i);
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            char cEnd = str.charAt(windowEnd);

            if (!charFrequency.containsKey(cEnd)) {
                windowStart = Math.max(windowStart, windowEnd);

                if (numCharsInPattern == 0) {
                    return true;
                } else {
                    numCharsInPattern = pattern.length();
                }
            } else {
                if (windowEnd == 0 || (windowEnd > 0 && cEnd != str.charAt(windowEnd - 1))) {
                    --numCharsInPattern;
                }
            }
        }

        return numCharsInPattern == 0 ? true : false;
    }

    /**
     * This problem is the fixed-size sliding window based on the length of pattern.
     *
     * @param str
     * @param pattern
     * @return
     */
    private static boolean findPermutationV2(String str, String pattern) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) // character is completely matched
                    matched++;
            }

            if (matched == charFrequencyMap.size())
                return true;

            if (windowEnd >= pattern.length() - 1) { // shrink the window when the size of substring is greater than pattern's length
                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0)
                        matched--; // before putting the character back, decrement the matched count
                    // put the character back for matching
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return false;
    }
}
