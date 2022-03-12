package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/qA5wW7R8ox7
 *
 * Given an array of numbers sorted in ascending order, find the floor of a given number ‘key’.
 * The floor of the ‘key’ will be the biggest element in the given array smaller than or equal to the ‘key’
 * Write a function to return the index of the floor of the ‘key’. If there isn’t a floor, return -1.
 *
 * Example 1:
 * Input: [4, 6, 10], key = 6
 * Output: 1
 * Explanation: The biggest number smaller than or equal to '6' is '6' having index '1'.
 *
 * Example 2:
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 3
 * Explanation: The biggest number smaller than or equal to '12' is '10' having index '3'.
 *
 * Example 3:
 * Input: [4, 6, 10], key = 17
 * Output: 2
 * Explanation: The biggest number smaller than or equal to '17' is '10' having index '2'.
 *
 * Example 4:
 * Input: [4, 6, 10], key = -1
 * Output: -1
 * Explanation: There is no number smaller than or equal to '-1' in the given array.
 *
 */
public class FloorNumber {

    public static void main(String[] args) {
//        int[] arr = {4, 6, 10};
//        int key = 6;

//        int[] arr = {1, 3, 8, 10, 15};
//        int key = 12;
//        int key = 7;

        int[] arr = {4, 6, 10};
        int key = 17;

//        int[] arr = {4, 6, 10};
//        int key = -1;

        int result = searchFloorOfANumber(arr, key);
        System.out.println("Result: " + result);
    }

    public static int searchFloorOfANumber(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        int pos = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) {
                return mid;
            }

            if (key < arr[mid]) {
                end = mid - 1;
            } else {
                pos = mid;
                start = mid + 1;
            }
        }

        return pos;
    }

}
