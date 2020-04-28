package com.manhpd;

/**
 * Given a sorted array, find the position of an item with value k in this array
 *
 * Input: arr = {1, 2, 3, 4, 5, 6, 7}; k = 2
 * Output: 1
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 2;

//        int position = binarySearchIterative(arr, k);
        int position = binarySearchRecursive(arr, k, 0, arr.length - 1);
        System.out.println(position);
    }

    public static int binarySearchIterative(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
//            int mid = left + (right - left) / 2;
            int mid = (left + right) >>> 1;

            if (arr[mid] == k) {
                return mid;
            } else if (k < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int k, int left, int right) {
        if (left > right) {
            return -1;
        }

//        int mid = left + (right - left) / 2;
        int mid = (left + right) >>> 1;
        if (arr[mid] == k) {
            return mid;
        } else if (k < arr[mid]) {
            return binarySearchRecursive(arr, k, left, mid - 1);
        } else {
            return binarySearchRecursive(arr, k, mid + 1, right);
        }
    }

}
