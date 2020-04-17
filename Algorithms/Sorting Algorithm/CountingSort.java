package com.manhpd;

import java.util.stream.IntStream;

public class CountingSort {

    public static void main(String[] args) {
        int[] arr = {4, 8, 4, 2, 9, 9, 6, 2, 9};
        countingSort(arr);
    }

    public static void countingSort(int[] arr) {
        int length = arr.length;
        int[] counts = new int[101];
        int[] nextIndex = new int[101];
        int[] output = new int[length];

        // initialize each elements of counts[i]
        for (int i = 0; i < 101; ++i) {
            counts[i] = 0;
            nextIndex[i] = 0;
        }

        // update the number of times that values appears
        for (int i = 0; i < length; ++i) ++counts[arr[i]];

        // calculate actual positions of elements in arr array based on counts array
        for (int i = 1; i < 101; ++i) {
            nextIndex[i] = nextIndex[i - 1] + counts[i - 1];
        }

        for (int i = 0; i < length; ++i) {
            output[nextIndex[arr[i]]] = arr[i];
            ++nextIndex[arr[i]];
        }

        IntStream.of(output).forEach(item -> System.out.print(item + ", "));
    }

}
