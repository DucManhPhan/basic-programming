package com.manhpd;

/**
 * Given a string s and a string t, check if s is subsequence of t.
 *
 * A subsequence of a string is a new string which is formed from the original string
 * by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Example 1:
 *     - Input: s = "abc", t = "ahbgdc"
 *     - Output: true
 *
 * Example 2:
 *     - Input: s = "axc", t = "ahbgdc"
 *     - Output: false
 *
 * Constraints:
 *     - 0 <= s.length <= 100
 *     - 0 <= t.length <= 10^4
 *     - Both strings consists only of lowercase characters.
 *
 */
public class IsSubsequence {

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
//        String s = "axc";
//        String t = "ahbgdc";

        boolean res = isSubsequence(s, t);
        System.out.println(res);
    }

    public static boolean isSubsequence(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        int sLeftIdx = 0;
        int tLeftIdx = 0;

        while (sLeftIdx < sLength && tLeftIdx < tLength) {
            if (s.charAt(sLeftIdx) == t.charAt(tLeftIdx)) {
                ++sLeftIdx;
            }

            ++tLeftIdx;
        }

        return sLeftIdx == sLength;
    }

}
