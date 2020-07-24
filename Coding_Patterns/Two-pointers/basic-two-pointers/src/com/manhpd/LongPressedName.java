package com.manhpd;

/**
 * Your friend is typing his name into a keyboard.
 * Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard.
 * Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 *
 * Example 1:
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 *
 * Example 2:
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 *
 * Example 3:
 * Input: name = "leelee", typed = "lleeelee"
 * Output: true
 *
 * Example 4:
 * Input: name = "laiden", typed = "laiden"
 * Output: true
 * Explanation: It's not necessary to long press any character.
 *
 * Constraints:
 *
 * 1 <= name.length <= 1000
 * 1 <= typed.length <= 1000
 * The characters of name and typed are lowercase letters.
 *
 */
public class LongPressedName {

    public static void main(String[] args) {
//        String name = "leelee";
//        String typed = "lleeelee";

        String name = "saeed";
        String typed = "ssaaedd";

        boolean res = isLongPressedName(name, typed);
        System.out.println(res);
    }

    public static boolean isLongPressedName0(String name, String typed) {
        int iName = 0;
        int iTyped = 0;

        for (; iName < name.length() && iTyped < typed.length();) {
            char nameChar = name.charAt(iName);
            char typedChar = typed.charAt(iTyped);
            char nextNameChar = iName < name.length() - 1 ? name.charAt(iName + 1) : 0;

            if (nameChar == typedChar && nextNameChar != nameChar) {
                ++iTyped;
            } else {
                ++iName;
            }
        }

        return (iTyped == typed.length()) || (iName == name.length() - 1);
    }

    public static boolean isLongPressedName(String name, String typed) {
        NumChar[] numNameChars = new NumChar[1000];
        NumChar[] numTypedChars = new NumChar[1000];

        for (int iName = 0, count = 0; iName < name.length(); ++iName) {
            char nameChar = name.charAt(iName);
            if (nameChar != numNameChars[count].c) {
                ++count;
            }

            numNameChars[count].c = nameChar;
            ++numNameChars[count].num;
        }

        for (int iTyped = 0; iTyped < name.length(); ++iTyped) {

        }

    }

    class NumChar {
        char c = 0;
        int num = 0;
    }

}
