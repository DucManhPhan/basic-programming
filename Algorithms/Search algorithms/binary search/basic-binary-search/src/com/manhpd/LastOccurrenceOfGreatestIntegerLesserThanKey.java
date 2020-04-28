package com.manhpd;

/**
 * Given a sorted array, find the index of the last occurrence of the greatest element that is less than key
 *
 * Input : arr = {2, 3, 3, 5, 5, 5, 6, 6}
 *
 * k = 2
 * Output: -1
 *
 * k = 5
 * Output: 2
 *
 */
public class LastOccurrenceOfGreatestIntegerLesserThanKey {

    public static void main(String[] args) {
        int[] arr = {2, 3, 3, 5, 5, 5, 6, 6};
//        int key = 5;
        int key = 2;

        int position = greatestLess(arr, key);
        System.out.println(position);
    }

    public static int greatestLess(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                right = mid - 1;
            } else if (key < arr[mid]) {
                right = mid - 1;
            } else {
                ans = mid;
                left = mid + 1;
            }
        }

        return ans;
    }

}
