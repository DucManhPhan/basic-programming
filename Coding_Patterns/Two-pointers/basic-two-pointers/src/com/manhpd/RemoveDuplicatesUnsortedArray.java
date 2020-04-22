package com.manhpd;

/**
 * Given an unsorted array of numbers and a target 'key',
 * remove all instances of 'key' in-place and return the new length of the array.
 *
 * Ex1: Input: [3, 2, 3, 6, 3, 10, 9, 3], Key=3
 * Output: 4
 * Explanation: The first four elements after removing every 'Key' will be [2, 6, 10, 9].
 *
 * Ex2: Input: [2, 11, 2, 2, 1], Key=2
 * Output: 2
 * Explanation: The first two elements after removing every 'Key' will be [11, 1].
 *
 */
public class RemoveDuplicatesUnsortedArray {

    public static void main(String[] args) {
//        int[] arr = {3, 2, 3, 6, 3, 10, 9, 3};
//        int key = 3;

        int[] arr = {2, 11, 2, 2, 1};
        int key = 2;

        int result = remove(arr, key);
        System.out.println(result);
    }

    public static int remove(int[] arr, int key) {
        int left = 0;
        int size = arr.length;

        for (int right = 0; right < size; ++right) {
            if (arr[right] != key) {
                arr[left++] = arr[right];
            }
        }

        return left;
    }

}
