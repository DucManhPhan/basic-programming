package com.manhpd;

/**
 * Given an sorted array, find the index(first occurrence) of least integer greater than key.
 *
 * Input: arr = {2, 3, 3, 5, 5, 5, 6, 6}
 *
 * k = 2
 * Output: 1
 *
 * k = 5
 * Output: 6
 */
public class FirstOccurrenceOfLeastIntegerGreaterThanKey {

    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 5, 5, 5, 6, 6};
        int key = 5;

        int position = leastGreater(arr, key);
        System.out.println(position);
    }

    public static int leastGreater(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                left = mid + 1;
            } else if (key < arr[mid]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

}
