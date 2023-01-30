package com.manhpd;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 *
 */
public class ConvertNumberToCharacter {

//    private static Map<Integer, String> mapValues = new HashMap<>() {{
//        put()
//    }};

    public static void main(String[] args) {
//        int i = 123454784;
//        int i = 1234567;
//        int i = 1300000;
//        int i = 50;
//        int i = 1234;
//        int i = 4;
//        int i = 100;

        int[] testcase = {1000000000, 1005, 10500, 10050, 12500, 1234, 1234567, 1, 12, 123, 100, 123456789, 4, 50, 105};
        for (int i : testcase) {
            String res = numberToString(i);
            System.out.println(i + " - " + res);
        }

    }

//    public static String numberToString(int a) {
////        Map<Character, String> mapIntToChar = new HashMap<>() {{
////            put('0', "");
////            put('1', "mot ");
////            put('2', "hai ");
////            put('3', "ba ");
////            put('4', "tu ");
////            put('5', "nam ");
////            put('6', "sau ");
////            put('7', "bay ");
////            put('8', "tam ");
////            put('9', "chin ");
////        }};
////
////        Stack<String> stk = new Stack<>();
////        String[] values = {" ", " ", "muoi ", "tram ", "nghin ", "", "", "trieu "};
////        char[] nums = Integer.valueOf(a).toString().toCharArray();
////        if (nums.length > MAX_LENGTH_INPUT) {
////            return "";
////        }
////
////        int len = nums.length;
////        int count = 0;
////        for (int i = len - 1; i >= 0; --i) {
////            StringBuilder sb = new StringBuilder();
////            char c = nums[i];
////            sb.append(mapIntToChar.get(c));
////            ++count;
////
////            if (count < 4) {
////                if (c == '0') {
////                    continue;
////                }
////
////                sb.append(values[count % 4]);
////            } else if (count > 3 && count < 7) {
////                if (count == 4) {
////                    sb.append(values[count]);
////                } else {
////                    if (c == '0') {
////                        continue;
////                    }
////
////                    sb.append(values[(count - 3) % 4]);
////                }
////            } else {
////                if (count == 7) {
////                    sb.append(values[count]);
////                } else {
////                    if (c == '0') {
////                        continue;
////                    }
////                    sb.append(values[(count - 6) % 4]);
////                }
////            }
////
////            stk.push(sb.toString());
////        }
////
////        StringBuilder res = new StringBuilder();
////        while (!stk.isEmpty()) {
////            res.append(stk.pop());
////        }
////
////        return res.toString();
////    }

    public static String numberToString(int a) {
        Map<Character, String> mapIntToChar = new HashMap<>() {{
            put('0', "");
            put('1', "mot ");
            put('2', "hai ");
            put('3', "ba ");
            put('4', "bon ");
            put('5', "nam ");
            put('6', "sau ");
            put('7', "bay ");
            put('8', "tam ");
            put('9', "chin ");
        }};

        Stack<String> stk = new Stack<>();
        String[] values = {" ", " ", "muoi ", "tram ", "nghin ", "", "", "trieu "};
        String nums = Integer.valueOf(a).toString();
        if (nums.length() > 9) {
            return "";
        }

        int count = 0;
        boolean isPreviousNotZeroNumber = false;
        for (int i = nums.length() - 1; i >= 0; --i) {
            StringBuilder sb = new StringBuilder();
            char c = nums.charAt(i);
            ++count;

            getSmallerThousandPart(c, count, sb, nums.length(), isPreviousNotZeroNumber, values, mapIntToChar);
            getLargerThousandPart(c, count, sb, nums.length(), values, mapIntToChar);
            getMillionPart(c, count, sb, nums.length(), values, mapIntToChar);

            stk.push(sb.toString());
            isPreviousNotZeroNumber = c != '0' ? true : false;
        }

        StringBuilder res = new StringBuilder();
        while (!stk.isEmpty()) {
            res.append(stk.pop());
        }

        return res.toString();
    }

    public static void getSmallerThousandPart(char c, int count, StringBuilder number, int len, boolean isPreviousNotZeroNumber, String[] values, Map<Character, String> mapIntToChar) {
        if (count < 4) {    // unit part
            if (count < len && count % 4 == 1 && c == '4') {
                number.append("tu ");
            } else if (count == len && count == 2 && c == '1') {
                // do nothing
            } else {
                number.append(mapIntToChar.get(c));
            }

            if (c == '0') {
                if (isPreviousNotZeroNumber) {
                    number.append("linh ");
                }

                return;
            }

            number.append(values[count % 4]);
        }
    }

    public static void getLargerThousandPart(char c, int count, StringBuilder number, int len, String[] values, Map<Character, String> mapIntToChar) {
        if (count > 3 && count < 7) {   // hundred part
            if (count < len && (count - 3) % 4 == 1 && c == '4') {  // 4 is at unit position
                number.append("tu ");
            } else if (count == len && count == 5 && c == '1') {    // 1 is at thousand part
                // do nothing
            } else {
                number.append(mapIntToChar.get(c));
            }

            if (count == 4) {
                number.append(values[count]);
            } else {
                if (c == '0') {
                    return;
                }

                number.append(values[(count - 3) % 4]);
            }
        }
    }

    public static void getMillionPart(char c, int count, StringBuilder number, int len, String[] values, Map<Character, String> mapIntToChar) {
        if (count > 6) {    // million part
            if (count < len && (count - 6) % 4 == 1 && c == '4') {
                number.append("tu ");
            } else {
                number.append(mapIntToChar.get(c));
            }

            if (count == 7) {
                number.append(values[count]);
            } else {
                if (c == '0') {
                    return;
                }

                number.append(values[(count - 6) % 4]);
            }
        }
    }

}
