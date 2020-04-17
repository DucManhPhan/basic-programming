package com.manhpd;

/**
 * Given an array of sorted numbers, remove all duplicates from it.
 * You should not use any extra space; after removing the duplicates in-place return the new length of the array.
 *
 * Ex1: Input: [2, 3, 3, 3, 6, 9, 9]
 * Output: 4
 * Explanation: The first four elements after removing the duplicates will be [2, 3, 6, 9].
 *
 * Ex2: Input: [2, 2, 2, 11]
 * Output: 2
 * Explanation: The first two elements after removing the duplicates will be [2, 11].
 */
public class RemoveDuplicatesSortedArray {

    public static void main(String[] args) {
//        int[] arr = {2, 3, 3, 3, 6, 9, 9};
        int[] arr = {2, 2, 2, 11};

        int result = remove(arr);
        System.out.println(result);
    }

    public static int remove(int[] arr) {
        int left = 0;
        int right = left + 1;
        int numDuplication = 0;

        while (right < arr.length) {
            if (arr[left] == arr[right]) {
                ++numDuplication;
            }

            ++left;
            ++right;
        }

        return numDuplication == 0 ? -1 : arr.length - numDuplication;
    }

    public static int removeStandard(int[] arr) {
        int nextNonDuplicate = 1; // index of the next non-duplicate element
        for (int i = 1; i < arr.length; i++) {
            if (arr[nextNonDuplicate - 1] != arr[i]) {
                arr[nextNonDuplicate] = arr[i];
                nextNonDuplicate++;
            }
        }

        return nextNonDuplicate;
    }
}
