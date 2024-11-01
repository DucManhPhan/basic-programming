package com.manhpd.java;


import java.util.Collections;
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
        // Example 1
//        String str = "aabccbb";
//        int k = 2;
//        int expected = 5;

        // Example 2
//        String str = "abbcb";
//        int k = 1;
//        int expected = 4;

        // Example 3
//        String str = "abccde";
//        int k = 1;
//        int expected = 3;

        // Example 4
        String str = "aabbaca";
        int k = 2;
        int expected = 5;

        System.out.printf("Result = %d, Expected = %d", getLongestSubstringV2(str, k), expected);
    }


    public static int getLongestSubstring(String str, int k) {
        int windowStart = 0;
        int maxLength = Integer.MIN_VALUE;

        Map<Character, Integer> characterFrequency = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            char cEnd = str.charAt(windowEnd);
            characterFrequency.put(cEnd, characterFrequency.getOrDefault(cEnd, 0) + 1);

            Map.Entry<Character, Integer> maxSameElements = Collections.max(characterFrequency.entrySet(), Map.Entry.comparingByValue());
            int numReplacedCharacters = (windowEnd - windowStart + 1) - maxSameElements.getValue();

            if (numReplacedCharacters > k) {
                char cStart = str.charAt(windowStart);
                characterFrequency.put(cStart, characterFrequency.get(cStart) - 1);

                ++windowStart;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    /**
     * Optimized the above version by using maxLengthSameLetters
     * to contain the maximum number of the same letters in the substring
     * without iterate all elements of Hashmap.
     *
     * @param str
     * @param k
     * @return
     */
    private static int getLongestSubstringV2(String str, int k) {
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
