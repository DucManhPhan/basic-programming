package com.manhpd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of distinct integers arr and an array of integer arrays pieces, where the integers in pieces are distinct. Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not allowed to reorder the integers in each array pieces[i].
 * Return true if it is possible to form the array arr from pieces. Otherwise, return false.
 * <p>
 * Example 1:
 * Input: arr = [85], pieces = [[85]]
 * Output: true
 * <p>
 * Example 2:
 * Input: arr = [15,88], pieces = [[88],[15]]
 * Output: true
 * Explanation: Concatenate [15] then [88]
 * <p>
 * Example 3:
 * Input: arr = [49,18,16], pieces = [[16,18,49]]
 * Output: false
 * Explanation: Even though the numbers match, we cannot reorder pieces[0].
 * <p>
 * Example 4:
 * Input: arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
 * Output: true
 * Explanation: Concatenate [91] then [4,64] then [78]
 * <p>
 * Example 5:
 * Input: arr = [1,3,5,7], pieces = [[2,4,6,8]]
 * Output: false
 * <p>
 * Constraints:
 * 1 <= pieces.length <= arr.length <= 100
 * sum(pieces[i].length) == arr.length
 * 1 <= pieces[i].length <= arr.length
 * 1 <= arr[i], pieces[i][j] <= 100
 * The integers in arr are distinct.
 * The integers in pieces are distinct (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
 */
public class CheckArrayFormation {

    public static void main(String[] args) {
        int[] arr = {91, 4, 64, 78};
        int[][] pieces = {{78}, {4, 64}, {91}};

//        int[] arr = {1, 2, 3};
//        int[][] pieces = {{2}, {1, 3}};

        CheckArrayFormation solution = new CheckArrayFormation();
        System.out.println(solution.canFormArrayII(arr, pieces));
    }

    /**
     * Using brute-force algorithm
     *
     * @param arr
     * @param pieces
     * @return
     */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        int lenPieces = pieces.length;

        for (int i = 0; i < lenPieces; ++i) {
            int[] piece = pieces[i];

            int prevIdx = Integer.MIN_VALUE;
            for (int j = 0; j < piece.length; ++j) {
                int idx = this.sequenceSearch(arr, piece[j]);

                if (idx < 0) {
                    return false;
                }

                if (prevIdx == Integer.MIN_VALUE) {
                    prevIdx = idx;
                    continue;
                }

                if (idx != prevIdx + 1) {
                    return false;
                }

                prevIdx = idx;
            }
        }

        return true;
    }

    /**
     * Optimize the brute force solution by using HashMap
     *
     * @param arr
     * @param pieces
     * @return
     */
    public boolean canFormArrayII(int[] arr, int[][] pieces) {
        Map<Integer, Integer> idxElemsArr = new HashMap<>();
        for (int i = 0; i < arr.length; ++i) {
            idxElemsArr.put(arr[i], i);
        }

        for (int[] piece : pieces) {
            if (!idxElemsArr.containsKey(piece[0])) {
                return false;
            }

            int idx = idxElemsArr.get(piece[0]);
            for (int i = 1; i < piece.length; ++i) {
                if (!idxElemsArr.containsKey(piece[i]) || idxElemsArr.get(piece[i]) != ++idx) {
                    return false;
                }
            }
        }

        return true;
    }


    private int sequenceSearch(int[] arr, int key) {
        int len = arr.length;

        for (int i = 0; i < len; ++i) {
            if (key == arr[i]) {
                return i;
            }
        }

        return Integer.MIN_VALUE;
    }

    /**
     * Using HashMap to save its index
     *
     * @param arr
     * @param pieces
     * @return
     */
    public boolean canFormArrayI(int[] arr, int[][] pieces) {
        Map<Integer, int[]> idxNumPieces = new HashMap<>();

        for (int i = 0; i < pieces.length; ++i) {
            idxNumPieces.put(pieces[i][0], pieces[i]);
        }

        for (int i = 0; i < arr.length; ++i) {
            if (idxNumPieces.containsKey(arr[i])) {
                int length = idxNumPieces.get(arr[i]).length;
                int start = 1;
                int[] piece = idxNumPieces.get(arr[i]);

                while (start < length) {
                    ++i;
                    if (arr[i] != piece[start]) {
                        return false;
                    }

                    ++start;
                }

            } else {
                return false;
            }
        }

        return true;
    }

}
