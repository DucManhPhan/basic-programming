package com.manhpd;

/**
 * Given an array arr of unsorted numbers and a target sum,
 * count all triplets in it such that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices.
 * Write a function to return the count of such triplets.
 *
 * Example 1:
 * Input: [-1, 0, 2, 3], target=3
 * Output: 2
 * Explanation: There are two triplets whose sum is less than the target: [-1, 0, 3], [-1, 0, 2]
 *
 * Example 2:
 * Input: [-1, 4, 2, 1, 3], target=5
 * Output: 4
 * Explanation: There are four triplets whose sum is less than the target:
 *    [-1, 1, 4], [-1, 1, 3], [-1, 1, 2], [-1, 2, 3]
 */
public class TripletsSmallerSum {

    public static void main(String[] args) {
        int[] arr = {-1, 0, 2, 3};
//        int[] arr = {-1, 4, 2, 1, 3};

        int target = 3;
//        int target = 5;

        int count = searchTripletsWith3Loops(arr, target);
        System.out.println("Count: " + count);
    }

    public static int searchTripletsWith3Loops(int[] arr, int target) {
        int count = -1;
        // TODO: Write your code here
        return count;
    }

    public static int searchTripletsWithBacktracking(int[] arr, int target) {
        int count = -1;
        // TODO: Write your code here
        return count;
    }

    public static int searchTripletsWith3Pointers(int[] arr, int target) {
        int count = -1;
        // TODO: Write your code here
        return count;
    }

}
