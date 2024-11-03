package com.manhpd.java;

import java.util.*;

/**
 * Given a string and a pattern, find all anagrams of the pattern in the given string.
 *
 * Every anagram is a permutation of a string. As we know, when we are not allowed to repeat characters while finding permutations of a string,
 * we get N! permutations (or anagrams) of a string having N characters.
 *
 * For example, here are the six anagrams of the string “abc”:
 *
 * abc
 * acb
 * bac
 * bca
 * cab
 * cba
 *
 * Write a function to return a list of starting indices of the anagrams of the pattern in the given string.
 *
 * Example 1:
 *
 * Input: String="ppqp", Pattern="pq"
 * Output: [1, 2]
 * Explanation: The two anagrams of the pattern in the given string are "pq" and "qp".
 * Example 2:
 *
 * Input: String="abbcabc", Pattern="abc"
 * Output: [2, 3, 4]
 * Explanation: The three anagrams of the pattern in the given string are "bca", "cab", and "abc".
 */
public class StringAnagrams {
    public static void main(String[] args) {
        // Example 1
//        String str = "ppqp";
//        String pattern = "pq";
//        List<Integer> expectedIndices = Arrays.asList(1, 2);

        // Example 2
//        String str = "abbcabc";
//        String pattern = "abc";
//        List<Integer> expectedIndices = Arrays.asList(2, 3, 4);

        // Example 3
        String str = "aaacb";
        String pattern = "abc";
        List<Integer> expectedIndices = Arrays.asList(2, 3, 4);

        List<Integer> result = findStringAnagrams(str, pattern);
        System.out.println("Result: " + result.toString() + ", expected: " + expectedIndices.toString());
    }

    public static List<Integer> findStringAnagrams(String str, String pattern) {
        int windowStart = 0;
        int matched = 0;

        List<Integer> resultIndices = new ArrayList<Integer>();
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            char cEnd = str.charAt(windowEnd);

            if (charFrequency.containsKey(cEnd)) {
                charFrequency.put(cEnd, charFrequency.get(cEnd) - 1);

                if (charFrequency.get(cEnd) == 0) {
                    ++matched;
                }
            }

            if (matched == charFrequency.size()) {
                for (int i = windowEnd - pattern.length() + 1; i <= windowEnd; ++i) {
                    resultIndices.add(i);
                }

                return resultIndices;
            }

            if (windowEnd >= pattern.length() - 1) {
                char cStart = str.charAt(windowStart);

                if (charFrequency.containsKey(cStart)) {
                    if (charFrequency.get(cStart) == 0) {
                        --matched;
                    }

                    charFrequency.put(cStart, charFrequency.get(cStart) + 1);
                }

                ++windowStart;
            }
        }

        return resultIndices;
    }
}
