package com.manhpd;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/mymvP915LY9
 *
 * Given an array of numbers sorted in ascending order, find the element in the array that has the minimum difference with the given ‘key’.
 *
 * Example 1:
 * Input: [4, 6, 10], key = 7
 * Output: 6
 * Explanation: The difference between the key '7' and '6' is minimum than any other number in the array
 *
 * Example 2:
 * Input: [4, 6, 10], key = 4
 * Output: 4
 *
 * Example 3:
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: 10
 *
 * Example 4:
 * Input: [4, 6, 10], key = 17
 * Output: 10
 *
 */
public class MinimumDifferenceElement {

    public static void main(String[] args) {
        System.out.println(MinimumDifferenceElement.searchMinDiffElementV2(new int[] { 4, 6, 10 }, 7));
        System.out.println(MinimumDifferenceElement.searchMinDiffElementV2(new int[] { 4, 6, 10 }, 4));
        System.out.println(MinimumDifferenceElement.searchMinDiffElementV2(new int[] { 1, 3, 8, 10, 15 }, 12));
        System.out.println(MinimumDifferenceElement.searchMinDiffElementV2(new int[] { 4, 6, 10 }, 17));
    }

    public static int searchMinDiffElementV2(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        int value = -1;
        int min = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = start + (end - start)/2;

            int tmpMin = Math.abs(key - arr[mid]);
            if (min > tmpMin) {
                min = tmpMin;
                value = arr[mid];
            }

            if (key > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return value;
    }

    public static int searchMinDiffElement(int[] arr, int key) {
        if (key < arr[0])
            return arr[0];
        if (key > arr[arr.length - 1])
            return arr[arr.length - 1];

        int start = 0, end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else {
                return arr[mid];
            }
        }

        // at the end of the while loop, 'start == end+1'
        // we are not able to find the element in the given array
        // return the element which is closest to the 'key'
        if ((arr[start] - key) < (key - arr[end]))
            return arr[start];
        return arr[end];
    }

}
