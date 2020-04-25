package com.manhpd;

/**
 * Upper case English alphabet to lower case
 */
public class ConvertUppercaseAndLowercase {

    private static int SPACE = 32;

    public static void main(String[] args) {
        String upperCase = "ABCDEFGHIJK";

//        long start = System.currentTimeMillis();
        String lowerCase = toLowerCaseUsingBitwise(upperCase);
//        long duration = System.currentTimeMillis() - start;
//
//        System.out.println("Time process for converting Upper case to Lower case using Bitwise: " + duration);
//
//        start = System.currentTimeMillis();
//        String lowerCase = upperCase.toLowerCase();
//        duration = System.currentTimeMillis() - start;
//
//        System.out.println("Time process for converting Upper case to Lower case using default method: " + duration);

        String tmp = toUpperCaseBitwise(lowerCase);

        System.out.println(tmp);
    }

    public static String toLowerCaseUsingBitwise(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            sb.append((char)(str.charAt(i) | SPACE));
        }

        return sb.toString();
    }

    public static String toUpperCaseBitwise(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            sb.append((char) (str.charAt(i) & (~SPACE)));
        }

        return sb.toString();
    }

}
