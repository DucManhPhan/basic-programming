package com.manhpd.patternLongestCommonSubstring;

/**
 * Given two strings ‘s1’ and ‘s2’, find the length of the longest subsequence which is common in both the strings.
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: s1 = "abdca"
 *        s2 = "cbda"
 * Output: 3
 * Explanation: The longest common subsequence is "bda".
 *
 * Example 2:
 * Input: s1 = "passport"
 *        s2 = "ppsspt"
 * Output: 5
 * Explanation: The longest common subsequence is "psspt".
 *
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String s1 = "abdca";
        String s2 = "cbda";

        int res = findLCSLength(s1, s2);
        System.out.println("Result: " + res);
    }

    public static int findLCSLength(String s1, String s2) {
        // TODO: Write your code here


        return -1;
    }

}
