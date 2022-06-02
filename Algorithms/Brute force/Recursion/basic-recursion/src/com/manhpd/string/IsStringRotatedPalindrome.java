package com.manhpd.string;

/**
 * Ref: https://www.techiedelight.com/check-given-string-rotated-palindrome-not/
 *
 * Given a string, check if it is a rotated palindrome or not.
 *
 * For example,
 * CBAABCD is a rotated palindrome as it is a rotation of palindrome ABCDCBA.
 * BAABCC is a rotated palindrome as it is a rotation of palindrome ABCCBA.
 *
 */
public class IsStringRotatedPalindrome {

    public static void main(String[] args) {
        String str = "CBAABCD";
//        String str = "BAABCC";

        boolean res = isRotatedPalindrome(str);
        System.out.println("Result: " + res);
    }

    public static boolean isRotatedPalindrome(String input) {


        return false;
    }

}
