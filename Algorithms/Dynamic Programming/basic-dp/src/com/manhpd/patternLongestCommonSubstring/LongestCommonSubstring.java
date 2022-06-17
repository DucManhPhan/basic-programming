package com.manhpd.patternLongestCommonSubstring;

/**
 * Given two strings ‘s1’ and ‘s2’, find the length of the longest substring which is common in both the strings.
 *
 * Example 1:
 * Input: s1 = "abdca"
 *        s2 = "cbda"
 * Output: 2
 * Explanation: The longest common substring is "bd".
 *
 * Example 2:
 * Input: s1 = "passport"
 *        s2 = "ppsspt"
 * Output: 3
 * Explanation: The longest common substring is "ssp".
 *
 */
public class LongestCommonSubstring {
    public static void main(String[] args) {
//        String str1 = "abdca";
//        String str2 = "cbda";

        String str1 = "passport";
        String str2 = "ppsspt";

//        int res = findLongestLengthSubstring(str1, str2);
        int res = findLongestLengthSubstringV2(str1, str2);
//        int res = findLongestLengthSubstringV3(str1, str2);
//        int res = findLongestLengthSubstringV4(str1, str2);
//        int res = findLongestLengthSubstringV5(str1, str2);
        System.out.println("Result: " + res);
    }

    /**
     * Using brute force for this problem
     * Time complexity: O(n^2 * m)
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int findLongestLengthSubstring(String str1, String str2) {
        String longestSubstring = "";
        boolean flag = false;

        for (int i = 0; i < str1.length(); ++i) {
            for (int j = i; j < str1.length(); ++j) {
                String tmp = str1.substring(i, j + 1);
                int idx = 0;

                for (int k = 0; k < str2.length(); ++k) {
                    if (idx == tmp.length()) {
                        break;
                    } else if (str2.charAt(k) == tmp.charAt(idx)) {
                        idx++;
                    } else {
                        idx = 0;
                    }
                }

                if (idx == tmp.length()) {
                    flag = true;

                    if (longestSubstring.length() < tmp.length()) {
                        longestSubstring = tmp;
                    }
                }
            }
        }

        if (flag) {
            System.out.println("Output: " + longestSubstring);
        } else {
            System.out.println("No common substrings");
        }

        return longestSubstring.length();
    }

    /**
     * Using Recursion
     * Time complexity: O(3^n)
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int findLongestLengthSubstringV2(String str1, String str2) {
//        return findLongestLengthSubstringV2(str1, str2, 0, 0, 0);
        return findLongestLengthSubstringV2(str1, str2, 0, 0);
    }

    private static int findLongestLengthSubstringV2(String str1, String str2, int i1, int i2, int count) {
        // base case
        if (i1 == str1.length() || i2 == str2.length()) {
            return count;
        }

        if (str1.charAt(i1) == str2.charAt(i2)) {
            count = findLongestLengthSubstringV2(str1, str2, i1 + 1, i2 + 1, count + 1);
        }

        int c2 = findLongestLengthSubstringV2(str1, str2, i1, i2 + 1, 0);
        int c3 = findLongestLengthSubstringV2(str1, str2, i1 + 1, i2, 0);

        return Math.max(count, Math.max(c2, c3));
    }

    /**
     * Check the difference between two versions of this same method findLongestLengthSubstringV2()
     *
     * @param str1
     * @param str2
     * @param i1
     * @param i2
     * @return
     */
    private static int findLongestLengthSubstringV2(String str1, String str2, int i1, int i2) {
        // base case
        if (i1 == str1.length() || i2 == str2.length()) {
            return 0;
        }

        if (str1.charAt(i1) == str2.charAt(i2)) {
            return 1 + findLongestLengthSubstringV2(str1, str2, i1 + 1, i2 + 1);
        }

        int c2 = findLongestLengthSubstringV2(str1, str2, i1, i2 + 1);
        int c3 = findLongestLengthSubstringV2(str1, str2, i1 + 1, i2);

        return Math.max(c2, c3);
    }

    /**
     * Using Top-Down Dynamic Programming
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int findLongestLengthSubstringV3(String str1, String str2) {
        int maxLength = Math.min(str1.length(), str2.length());
        Integer[][][] dp = new Integer[str1.length()][str2.length()][maxLength];

        return findLCSLengthRecursive(dp, str1, str2, 0, 0, 0);
    }

    private static int findLCSLengthRecursive(Integer[][][] dp, String str1, String str2, int i1, int i2, int count) {
        if (i1 == str1.length() || i2 == str2.length()) {
            return count;
        }

        if (dp[i1][i2][count] != null) {
            return dp[i1][i2][count];
        }

        int c3 = count;
        if (str1.charAt(i1) == str2.charAt(i2)) {
            c3 = findLCSLengthRecursive(dp, str1, str2, i1 + 1, i2 + 1, count + 1);
        }

        int c1 = findLCSLengthRecursive(dp, str1, str2, i1, i2 + 1, 0);
        int c2 = findLCSLengthRecursive(dp, str1, str2, i1 + 1, i2, 0);

        dp[i1][i2][count] = Math.max(c1, Math.max(c2, c3));
        return dp[i1][i2][count];
    }

    /**
     * Using Bottom-Up Dynamic Programming
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int findLongestLengthSubstringV4(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int maxLength = 0;

        for (int i = 1; i <= str1.length(); ++i) {
            for (int j = 1; j <= str2.length(); ++j) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        return maxLength;
    }

    /**
     * Improve Bottom-Up Dynamic Programming
     *
     * Time complexity: O(m * n)
     * Space complexity: O(n)
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int findLongestLengthSubstringV5(String str1, String str2) {
        int[][] dp = new int[2][str2.length() + 1];
        int maxLength = 0;

        for (int i = 1; i <= str1.length(); ++i) {
            for (int j = 1; j <= str2.length(); ++j) {
                dp[i % 2][j] = 0;
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i % 2][j]);
                }
            }
        }

        return maxLength;
    }

}
