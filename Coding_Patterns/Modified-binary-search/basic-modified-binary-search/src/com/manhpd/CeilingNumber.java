package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/qA5wW7R8ox7
 *
 * Given an array of numbers sorted in an ascending order, find the ceiling of a given number ‘key’.
 * The ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to the ‘key’.
 *
 * Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling return -1.
 *
 * Example 1:
 * Input: [4, 6, 10], key = 6
 * Output: 1
 * Explanation: The smallest number greater than or equal to '6' is '6' having index '1'.
 *
 * Example 2:
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 4
 * Explanation: The smallest number greater than or equal to '12' is '15' having index '4'.
 *
 * Example 3:
 * Input: [4, 6, 10], key = 17
 * Output: -1
 * Explanation: There is no number greater than or equal to '17' in the given array.
 *
 * Example 4:
 * Input: [4, 6, 10], key = -1
 * Output: 0
 * Explanation: The smallest number greater than or equal to '-1' is '4' having index '0'.
 *
 */
public class CeilingNumber {

    public static void main(String[] args) {
//        int[] arr = {4, 6, 10};
//        int key = 6;

        int[] arr = {1, 3, 8, 10, 15};
        int key = 12;
//        int key = 7;

//        int[] arr = {4, 6, 10};
//        int key = 17;

//        int[] arr = {4, 6, 10};
//        int key = -1;

        int result = searchCeilingOfANumber(arr, key);
        System.out.println("Result: " + result);
    }

    /**
     * Using Binary Search algorithm
     *
     * @param arr
     * @param key
     * @return
     */
    public static int searchCeilingOfANumber(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        int pos = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) {
                return mid;
            }

            if (key < arr[mid]) {
                pos = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return pos;
    }

    /**
     * Using the algorithm
     *
     * @param arr
     * @param key
     * @return
     */
    public static int searchCeilingOfANumberV2(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        if (key > arr[end]) {
            return -1;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key > arr[mid]) {
                start = mid + 1;
            } else if (key < arr[mid]) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return start;
    }

}
