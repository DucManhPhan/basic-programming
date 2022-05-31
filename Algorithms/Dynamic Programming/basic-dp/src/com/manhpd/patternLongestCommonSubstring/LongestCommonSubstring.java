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
        String str1 = "abdca";
        String str2 = "cbda";

        int res = findLongestLengthSubstring(str1, str2);
        System.out.println("Result: " + res);
    }

    /**
     * Using brute force for this problem
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
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int findLongestLengthSubstringV2(String str1, String str2) {


        return 0;
    }


    }
