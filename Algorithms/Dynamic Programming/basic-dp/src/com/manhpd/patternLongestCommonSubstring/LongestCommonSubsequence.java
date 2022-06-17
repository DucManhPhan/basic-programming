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
//        String s1 = "cbda";
//        String s2 = "abdca";

        String s1 = "passport";
        String s2 = "ppsspt";

        int res = findLCSLength(s1, s2);
        System.out.println("Result: " + res);
    }

    public static int findLCSLength(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int maxLength = Integer.MIN_VALUE;

        for (int i = 1; i <= s1.length(); ++i) {
            for (int j = 1; j <= s2.length(); ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }

                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        return maxLength;
    }

}
