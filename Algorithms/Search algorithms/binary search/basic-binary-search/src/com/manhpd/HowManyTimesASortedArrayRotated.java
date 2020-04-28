package com.manhpd;

/**
 * Suppose a sorted array A is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The array will not contain duplicates.
 *
 * If you know the number of times the array is rotated, then this problem becomes trivial.
 * If the number of rotation is x, then minimum element is A[x].
 *
 */
public class HowManyTimesASortedArrayRotated {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int numOfRotation = findRotationCount(arr);
        System.out.println(numOfRotation);
    }

    /**
     * The number of the rotation time = the index of minimum element.
     *
     * @param arr
     * @return
     */
    public static int findRotationCount(int[] arr) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;

        while (left <= right) {
            if (arr[left] <= arr[right]) {  // Case 1: sorted array
                return left;
            }

            int mid = left + (right - left) / 2;
            int next = (mid + 1) % len;
            int prev = (mid + len - 1) % len;
            if (arr[mid] <= arr[next] && arr[mid] <= arr[prev]) {
                return mid;
            } else if (arr[mid] <= arr[right]) {
                right = mid - 1;
            } else if (arr[mid] >= arr[left]) {
                left = mid + 1;
            }
        }

        return -1;
    }

}
