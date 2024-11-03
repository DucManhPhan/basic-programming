package com.manhpd.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string and a list of words, find all the starting indices of substrings in the given string
 * that are a concatenation of all the given words exactly once without any overlapping of words.
 * It is given that all words are of the same length.
 *
 * Example 1:
 * Input: String="catfoxcat", Words=["cat", "fox"]
 * Output: [0, 3]
 * Explanation: The two substring containing both the words are "catfox" & "foxcat".
 *
 * Example 2:
 * Input: String="catcatfoxfox", Words=["cat", "fox"]
 * Output: [3]
 * Explanation: The only substring containing both the words is "catfox".
 *
 */
public class WordsConcatention {
    public static void main(String[] args) {
        // Example 1
//        String str = "catfoxcat";
//        String[] words = {"cat", "fox"};
//        List<Integer> expected = new ArrayList<Integer>(){{
//            add(0);
//            add(3);
//        }};

        // Example 2
        String str = "catcatfoxfox";
        String[] words = {"cat", "fox"};
        List<Integer> expected = new ArrayList<Integer>(){{
            add(3);
        }};

        List<Integer> res = findWordConcatenation(str, words);
        System.out.println("Result: " + res + ", expected: " + expected);
    }

    public static List<Integer> findWordConcatenation(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<Integer>();
        Map<Character, Integer> charFrequency = new HashMap<>();
        int wordsLength = 0;

        for (String s : words) {
            wordsLength += s.length();

            for (char c : s.toCharArray()) {
                charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
            }
        }

        int eachWordLength = wordsLength / words.length;
        int windowStart = 0;
        int matched = 0;

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            char cEnd = str.charAt(windowEnd);

            if (charFrequency.containsKey(cEnd)) {
                charFrequency.put(cEnd, charFrequency.get(cEnd) - 1);

                if (charFrequency.get(cEnd) == 0) {
                    ++matched;
                }
            }

            if (matched == wordsLength) {
                resultIndices.add(windowStart);
            }

            if (windowEnd - windowStart + 1 >= wordsLength) {
                for (int i = 0; i < eachWordLength; ++i) {
                    char cStart = str.charAt(windowStart++);

                    if (charFrequency.get(cStart) == 0) {
                        --matched;
                    }

                    charFrequency.put(cStart, charFrequency.get(cStart) + 1);
                }
            }
        }

        return resultIndices;
    }
}
