package com.manhpd;

/**
 * Given an array arr[] containing integers of size N, the task is to find the XOR of this array.
 *
 * Ex1: Input: arr[] = {2, 4, 7}
 * Output: 1
 * Explanation:
 * XOR of the array = 2 ^ 4 ^ 7 = 1
 *
 * Ex2: Input: arr[] = { 3, 9, 12, 13, 15 }
 * Output: 4
 *
 */
public class FindXORofElementsArray {

    public static void main(String[] args) {
//        int[] arr = {2, 4, 7};
        int[] arr = {3, 9, 12, 13, 15};
        int res = xorOfArray(arr);

        System.out.println(res);
    }

    public static int xorOfArray(int[] arr) {
        int result = 0;

        for (int value : arr) {
            result ^= value;
        }

        return result;
    }

}
