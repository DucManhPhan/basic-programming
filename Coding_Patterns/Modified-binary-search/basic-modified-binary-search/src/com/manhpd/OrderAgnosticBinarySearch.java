package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/R8LzZQlj8lO
 *
 * Given a sorted array of numbers, find if a given number ‘key’ is present in the array.
 * Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.
 * You should assume that the array can have duplicates.
 *
 * Write a function to return the index of the ‘key’ if it is present in the array, otherwise return -1.
 *
 * Example 1:
 * Input: [4, 6, 10], key = 10
 * Output: 2
 *
 * Example 2:
 * Input: [1, 2, 3, 4, 5, 6, 7], key = 5
 * Output: 4
 *
 * Example 3:
 * Input: [10, 6, 4], key = 10
 * Output: 0
 *
 * Example 4:
 * Input: [10, 6, 4], key = 4
 * Output: 2
 *
 */
public class OrderAgnosticBinarySearch {

    public static void main(String[] args) {
//        int[] arr = {4, 6, 10};
//        int key = 10;

//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        int key = 5;

        int[] arr = {10, 6, 4};
//        int key = 10;
        int key = 4;

        int result = search(arr, key);
        System.out.println("Result: " + result);
    }

    public static int search(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        boolean isAscendingOrder = false;

        if (arr[start] < arr[end]) {
            isAscendingOrder = true;
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (key == arr[mid]) {
                return mid;
            }

            if (isAscendingOrder) {
                if (key > arr[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                if (key > arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }

        return -1;
    }

}
