package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/g2w6QPBA2Nk
 * https://leetcode.com/problems/find-smallest-letter-greater-than-target/
 *
 * Given an array of lowercase letters sorted in ascending order, find the smallest letter in the given array greater than a given ‘key’.
 * Assume the given array is a circular list, which means that the last letter is assumed to be connected with the first letter.
 * This also means that the smallest letter in the given array is greater than the last letter of the array and is also the first letter of the array.
 *
 * Write a function to return the next letter of the given ‘key’.
 *
 * Example 1:
 * Input: ['a', 'c', 'f', 'h'], key = 'f'
 * Output: 'h'
 * Explanation: The smallest letter greater than 'f' is 'h' in the given array.
 *
 * Example 2:
 * Input: ['a', 'c', 'f', 'h'], key = 'b'
 * Output: 'c'
 * Explanation: The smallest letter greater than 'b' is 'c'.
 *
 * Example 3:
 * Input: ['a', 'c', 'f', 'h'], key = 'm'
 * Output: 'a'
 * Explanation: As the array is assumed to be circular, the smallest letter greater than 'm' is 'a'.
 *
 * Example 4:
 * Input: ['a', 'c', 'f', 'h'], key = 'h'
 * Output: 'a'
 * Explanation: As the array is assumed to be circular, the smallest letter greater than 'h' is 'a'.
 *
 * Example 5:
 * Input: ['e', 'e', 'e', 'e', 'e', 'e', 'n', 'n', 'n', 'n']
 * Output: 'n'
 *
 */
public class NextLetter {

    public static char searchNextLetter(char[] letters, char key) {
        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = start + (end - start)/2;

            if (key >= letters[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return letters[start % letters.length];
    }

    public static char searchNextLetterV2(char[] letters, char key) {
        int start = 0;
        int end = letters.length - 1;
        int pos = 0;

        if (key >= letters[end]) {
            return letters[start];
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key >= letters[mid]) {
                start = mid + 1;
            } else {
                pos = mid;
                end = mid - 1;
            }
        }

        return letters[pos];
    }

    public static void main(String[] args) {
        System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'f'));
        System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'b'));
        System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'm'));
        System.out.println(NextLetter.searchNextLetter(new char[] { 'a', 'c', 'f', 'h' }, 'h'));
    }

}
