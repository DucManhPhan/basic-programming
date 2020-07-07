package com.manhpd;

import java.util.stream.IntStream;

public class HeapSort {

    public static void main(String[] args) {
        int[] nums = {11, 3, 5, 4, 9, 15, 19, 7};
        heapSort(nums);

        IntStream.of(nums).forEach(value -> System.out.print(value + ", "));
    }

    /**
     * Ensure that the node's value at i index is greater than the left node and right node's values.
     *
     * Use recursion for its children nodes.
     *
     * @param nums
     * @param i
     */
    public static void heapify(int[] nums, int size, int i) {
        int left = 2 * (i + 1) - 1;
        int right = 2 * (i + 1);
        int largest = -1;

        largest = (left < size) && (nums[i] < nums[left]) ? left : i;
        largest = (right < size) && (nums[largest] < nums[right]) ? right : largest;

        // the children nodes are not satisfied this property
        if (largest != i) {
            // swap the nodes's value at the i and largest index
            int tmp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = tmp;

            heapify(nums, size, largest);
        }
    }

    public static void buildHeap(int[] nums) {
        int middleIdx = (nums.length / 2) - 1;

        for (int i = middleIdx; i >= 0; --i) {
            heapify(nums, nums.length, i);
        }
    }

    public static void heapSort(int[] nums) {
        buildHeap(nums);

        int tmp;
        for (int i = nums.length - 1; i > 0; --i) {
            // swap the largest value at the first index and the value of last index node
            tmp = nums[0];
            nums[0] = nums[i];
            nums[i] = tmp;

            heapify(nums, i, 0);
        }
    }

}
