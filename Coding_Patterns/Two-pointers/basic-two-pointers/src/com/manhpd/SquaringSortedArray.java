package com.manhpd;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Given a sorted array, create a new array containing squares of all the number of the input array in the sorted order.
 *
 * Ex1: Input: [-2, -1, 0, 2, 3]
 *      Output: [0, 1, 4, 4, 9]
 *
 * Ex2: Input: [-3, -1, 0, 1, 2]
 *      Output: [0 1 1 4 9]
 */
public class SquaringSortedArray {

    public static void main(String[] args) {
//        int[] arr = {-2, -1, 0, 2, 3};
        int[] arr = {-7, -3, 2, 3, 11};
//        int[] arr = {-3, -1, 0, 1, 2};

//        int[] results = makeSquares(arr);
        int[] results = makeSquaresII(arr);
        Arrays.stream(results).forEach(item -> System.out.println(item));
    }

    /**
     * Using sorting
     *
     * Time complexity: O(n * log(n))
     * Space complexity: O(n)
     *
     * @param arr
     * @return
     */
    public static int[] makeSquares(int[] arr) {
        int[] squares = new int[arr.length];

        for (int i = 0; i < arr.length; ++i) {
            int numSquare = (int) Math.pow(arr[i], 2);
            squares[i] = numSquare;
        }

        Arrays.sort(squares);
        return squares;
    }

    public static int[] makeSquaresII(int[] arr) {
        int[] squares = new int[arr.length];
        int highestIndex = arr.length - 1;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];

            if (leftSquare < rightSquare) {
                squares[highestIndex--] = rightSquare;
                --right;
            } else {
                squares[highestIndex--] = leftSquare;
                ++left;
            }
        }

        return squares;
    }

}
