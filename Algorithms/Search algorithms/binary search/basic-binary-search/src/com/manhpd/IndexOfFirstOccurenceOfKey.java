package com.manhpd;

/**
 * Given an sorted array, find the index of the first occurrence of a key.
 *
 * Input : arr = {2, 3, 3, 5, 5, 5, 6, 6};
 *
 * k = 3
 * Returns : 1
 *
 * k = 5
 * Returns : 3
 *
 * k = 4
 * Returns : -1
 */
public class IndexOfFirstOccurenceOfKey {

    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 5, 5, 5, 6, 6};
//        int k = 3;
        int k = 4;
//        int k = 5;

        int position = firstOccurrenceOf(arr, k);
        System.out.println(position);
    }

    public static int firstOccurrenceOf(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                ans = mid;
                right = mid - 1;
            } else if (key < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

}
