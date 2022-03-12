package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/xVlKmyX542P
 *
 * Given a string, find all of its permutations preserving the character sequence but changing case.
 *
 * Example 1:
 * Input: "ad52"
 * Output: "ad52", "Ad52", "aD52", "AD52"
 *
 * Example 2:
 * Input: "ab7c"
 * Output: "ab7c", "Ab7c", "aB7c", "AB7c", "ab7C", "Ab7C", "aB7C", "AB7C"
 *
 */
public class StringPermutationsChangingCase {

    public static void main(String[] args) {
        List<String> result = StringPermutationsChangingCase.findLetterCaseStringPermutations("ad52");
        System.out.println(" String permutations are: " + result);

        result = StringPermutationsChangingCase.findLetterCaseStringPermutations("ab7c");
        System.out.println(" String permutations are: " + result);
    }

    public static List<String> findLetterCaseStringPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        // TODO: Write your code here
        return permutations;
    }

}
