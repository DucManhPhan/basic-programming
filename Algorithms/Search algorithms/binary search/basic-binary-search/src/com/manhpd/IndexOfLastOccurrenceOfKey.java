package com.manhpd;

/**
 * Given a sorted array, find the index of last occurrence of key
 * Input : arr = {2, 3, 3, 5, 5, 5, 6, 6}
 *
 * k = 3
 * Returns : 2
 *
 * k = 5
 * Returns : 5
 *
 * k = 4
 * Returns : -1
 *
 */
public class IndexOfLastOccurrenceOfKey {

    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 5, 5, 5, 6, 6};
//        int key = 3;
//        int key = 4;
        int key = 5;

        int position = lastOccurrenceOf(arr, key);
        System.out.println(position);
    }

    public static int lastOccurrenceOf(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                ans = mid;
                left = mid + 1;
            } else if (key < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

}
