package com.manhpd.java;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a string with lowercase letters only, if you are allowed to replace no more than k letters with any letter,
 * find the length of the longest substring having the same letters after replacement.
 *
 * Input: String="aabccbb", k=2
 * Output: 5
 * Explanation: Replace the two 'c' with 'b' to have a longest repeating substring "bbbbb".
 *
 * Input: String="abbcb", k=1
 * Output: 4
 * Explanation: Replace the 'c' with 'b' to have a longest repeating substring "bbbb".
 *
 * Input: String="abccde", k=1
 * Output: 3
 * Explanation: Replace the 'b' or 'd' with 'c' to have the longest repeating substring "ccc".
 *
 */
public class LongestSubstringSameLettersReplacements {

    public static void main(String[] args) {
        String str = "abccde";
        int k = 1;

        System.out.println("Longest length of string is: " + getLongestSubstring(str, k));
    }

    private static int getLongestSubstring(String str, int k) {
        int windowStart = 0;
        int longestLength = 0;
        int maxLengthSameLetters = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            Character rightCharacter = str.charAt(windowEnd);
            charFrequencyMap.put(rightCharacter, charFrequencyMap.getOrDefault(rightCharacter, 0) + 1);
            maxLengthSameLetters = Math.max(maxLengthSameLetters, charFrequencyMap.get(rightCharacter));

            if (windowEnd - windowStart + 1 - maxLengthSameLetters > k) {
                Character leftCharacter = str.charAt(windowStart);
                charFrequencyMap.put(leftCharacter, charFrequencyMap.get(leftCharacter) - 1);
                ++windowStart;
            }

            longestLength = Math.max(longestLength, windowEnd - windowStart + 1);
        }

        return longestLength;
    }

}
