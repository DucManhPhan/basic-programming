package com.manhpd;

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
public class permutation_string {

    public static void main(String[] args) {
        String target = "odicf";
        String pattern = "dc";

        System.out.println("Permutation string is: " + isPermutationSubstring(target, pattern));
    }

    private static boolean isPermutationSubstring(String target, String pattern) {
        int windowStart = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        // set data for pattern
        for (int i = 0; i < pattern.length(); ++i) {
            charFrequencyMap.put(pattern.charAt(i), charFrequencyMap.getOrDefault(pattern.charAt(i), 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < target.length(); ++windowEnd) {
            Character rightCharacter = target.charAt(windowEnd);

        }

        if (charFrequencyMap.size() == 0) {
            return true;
        }

        return false;
    }

}
