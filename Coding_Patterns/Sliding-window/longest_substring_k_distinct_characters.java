package com.manhpd;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring in it with no more than K distinct characters.
 *
 * Input: String="araaci", K=2
 * Output: 4
 * Explanation: The longest substring with no more than '2' distinct characters is "araa".
 *
 * Input: String="araaci", K=1
 * Output: 2
 * Explanation: The longest substring with no more than '1' distinct characters is "aa".
 *
 * Input: String="cbbebi", K=3
 * Output: 5
 * Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".
 *
 */
public class longest_substring_k_distinct_characters {

    public static void main(String[] args) {
        String str = "araaci";
        int k = 3;

        System.out.println("Longest length of array is: " + getLongestLength(str, k));
    }

    private static int getLongestLength(String str, int k) {
        int longestLength = 0;
        int windowStart = 0;
        Map<Character, Integer> charWithNum = new HashMap<Character, Integer>();

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            char rightCharacter = str.charAt(windowEnd);
            charWithNum.put(rightCharacter, charWithNum.getOrDefault(rightCharacter, 0) + 1);

            while (charWithNum.size() > k) {
                char leftCharacter = str.charAt(windowStart);
                charWithNum.put(leftCharacter, charWithNum.get(leftCharacter) - 1);

                if (charWithNum.get(leftCharacter) == 0) {
                    charWithNum.remove(leftCharacter);
                }

                ++windowStart;
            }

            longestLength = Math.max(longestLength, windowEnd - windowStart + 1);
        }

        return longestLength;
    }

}