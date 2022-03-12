package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/R1B78K9oBEz
 *
 * Given an array of numbers sorted in ascending order, find the range of a given number ‘key’.
 * The range of the ‘key’ will be the first and last position of the ‘key’ in the array.
 *
 * Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1, -1].
 *
 * Example 1:
 * Input: [4, 6, 6, 6, 9], key = 6
 * Output: [1, 3]
 *
 * Example 2:
 * Input: [1, 3, 8, 10, 15], key = 10
 * Output: [3, 3]
 *
 * Example 3:
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: [-1, -1]
 *
 */
public class NumberRange {

    public static void main(String[] args) {
        int[] result = NumberRange.findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");

        result = NumberRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");

        result = NumberRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    }

    public static int[] findRange(int[] arr, int key) {
        int left = searchMostLeft(arr, key);
        int right = searchMostRight(arr, key);

        int[] result = new int[] { left, right };
        return result;
    }

    private static int searchMostRight(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        int pos = -1;
        while (start <= end) {
            int mid = start + (end - start)/2;

            if (arr[mid] == key) {
                pos = mid;
                start = mid + 1;
            } else if (key < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return pos;
    }

    private static int searchMostLeft(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        int pos = -1;
        while (start <= end) {
            int mid = start + (end - start)/2;

            if (arr[mid] == key) {
                pos = mid;
                end = mid - 1;
            } else if (key < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return pos;
    }

}
