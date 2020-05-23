package com.manhpd;

import java.util.stream.IntStream;

public class SelectionSort {

    public static void main(String[] args) {
        int[] nums = {44, 55, 12, 42, 94, 18, 6, 67};
        selectionSort(nums);

        IntStream.of(nums).forEach(item -> System.out.print(item + " --> "));
        System.out.println(" null ");
    }

    public static void selectionSort(int[] nums) {
        int length = nums.length;

        for (int i = 0; i < length; ++i) {
            int minIndex = i;

            for (int j = i + 1; j < length; ++j) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }

            // swap value at the minIndex and i
            int tmp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = tmp;
        }
    }

}
