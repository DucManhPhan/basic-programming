package com.manhpd.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/Y5YDWzqPn7O
 *
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
        String str = "catfoxcat";
        String[] words = {"cat", "fox"};

//        String str = "catcatfoxfox";
//        String[] words = {"cat", "fox"};

        List<Integer> res = findWordConcatenation(str, words);
        System.out.println("Result: " + res);
    }

    public static List<Integer> findWordConcatenation(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<Integer>();
        // TODO: Write your code here
        return resultIndices;
    }

}
