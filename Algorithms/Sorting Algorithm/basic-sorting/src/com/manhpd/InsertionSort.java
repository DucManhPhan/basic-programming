package com.manhpd;

import java.util.stream.IntStream;

public class InsertionSort {

    public static void main(String[] args) {
        int[] nums = {44, 55, 12, 42, 94, 18, 6, 67};
//        insertionSortVersion1(nums);
        insertionSortVersion2(nums);

        IntStream.of(nums).forEach(item -> {
            System.out.print(item + " --> ");
        });

        System.out.println(" null");
    }

    public static void insertionSortVersion1(int[] nums) {
        int length = nums.length;

        for (int i = 1; i < length; ++i) {
            for (int j = i; j > 0; --j) {
                if (nums[j] < nums[j - 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
            }
        }
    }

    public static void insertionSortVersion2(int[] nums) {
        int length = nums.length;

        for (int i = 1; i < length; ++i) {
            int key = nums[i];
            int j = i - 1;

            while (j >= 0 && key < nums[j]) {
                nums[j + 1] = nums[j];
                --j;
            }

            nums[j + 1] = key;
        }
    }

}
