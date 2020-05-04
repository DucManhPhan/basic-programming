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
        int[] arr = {6, 7, 8, 9, 10, 11, 12, 15, 0, 1, 2, 3, 4};
        int numOfRotation = findRotationCountByBinarySearch(arr);
        numOfRotation = findRotationCountByLinearSearch(arr);
        System.out.println(numOfRotation);
    }

    /**
     * The number of the rotation time = the index of minimum element.
     *
     * @param arr
     * @return
     */
    public static int findRotationCountByBinarySearch(int[] arr) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;
        int numComparisons = 1;

        while (left <= right) {
            if (arr[left] <= arr[right]) {  // Case 1: sorted array
                System.out.println("The number of comparison of using binary search is: " + numComparisons);
                ++numComparisons;
                return left;
            }

            int mid = left + (right - left) / 2;
            int next = (mid + 1) % len;
            int prev = (mid + len - 1) % len;
            if (arr[mid] <= arr[next] && arr[mid] <= arr[prev]) {   // Case 2: mid index points to the minimum element
                ++numComparisons;
                System.out.println("The number of comparison of using binary search is: " + numComparisons);
                return mid;
            } else if (arr[mid] <= arr[right]) {
                ++numComparisons;
                right = mid - 1;
            } else if (arr[mid] >= arr[left]) {
                ++numComparisons;
                left = mid + 1;
            }
        }

        System.out.println("The number of comparison of using binary search is: " + numComparisons);
        return -1;
    }

    public static int findRotationCountByLinearSearch(int[] arr) {
        int minPos = 0;
        int numComparisons = 0;

        for (int i = 1; i < arr.length - 1; ++i) {
            ++numComparisons;

            if (arr[minPos] > arr[i]) {
                minPos = i;
            }
        }

        System.out.println("The number of comparison of using linear search is: " + numComparisons);
        return minPos;
    }

}
