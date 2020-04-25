package com.manhpd;

/**
 * Implement a function that change the case of a string such that GeeksFoRgeekS turns gEEKSfOrGEEKs .
 */
public class InvertUpperCaseAndLowerCase {

    private static int DISTANCE_LOWER_UPPER = 32;

    public static void main(String[] args) {
        String str = "GeeksFoRgeekS";
        String result = invertCharacter(str);

        System.out.println(result);

//        boolean isUpperCase = isUpperCase('Z');
//        System.out.println(isUpperCase);
    }

    public static String invertCharacter(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (isUpperCase(ch)) {
                sb.append(toLowerCase(ch));
            } else {
                sb.append(toUpperCase(ch));
            }
        }

        return sb.toString();
    }

    public static char toUpperCase(char ch) {
        return (char) (ch & ~DISTANCE_LOWER_UPPER);
    }

    public static char toLowerCase(char ch) {
        return (char) (ch | DISTANCE_LOWER_UPPER);
    }

    public static boolean isUpperCase(char ch) {
        return (ch & DISTANCE_LOWER_UPPER) == 0;
    }

    public static boolean isLowerCase(char ch) {
        return !isUpperCase(ch);
    }

}
