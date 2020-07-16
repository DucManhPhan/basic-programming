package com.manhpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of unsorted numbers and a target number, find a triplet in the array whose sum is as close to the target number as possible, return the sum of the triplet.
 * If there are more than one such triplet, return the sum of the triplet with the smallest sum.
 *
 * Example 1:
 *
 * Input: [-2, 0, 1, 2], target=2
 * Output: 1
 * Explanation: The triplet [-2, 1, 2] has the closest sum to the target.
 * Example 2:
 *
 * Input: [-3, -1, 1, 2], target=1
 * Output: 0
 * Explanation: The triplet [-3, 1, 2] has the closest sum to the target.
 * Example 3:
 *
 * Input: [1, 0, 1, 1], target=100
 * Output: 3
 * Explanation: The triplet [1, 1, 1] has the closest sum to the target.
 *
 */
public class TripletSumCloseTarget {

    private static int smallestDistance = Integer.MAX_VALUE;

    public static void main(String[] args) {
//        int[] arr = {-2, 0, 1, 2};
//        int targetSum = 2;

//        int[] arr = {-3, -1, 1, 2};
//        int targetSum = 1;

//        int[] arr = {1, 2, 4, 8, 16, 32, 64, 128};
//        int targetSum = 82;

        int[] arr = {1, 0, 1, 1};
        int targetSum = 100;

//        int res = searchTriplet(arr, targetSum);
//        int res = searchTriplet0(arr, targetSum);
        int res = searchTriplet1(arr, targetSum);
        System.out.println(res);
    }

    /**
     * Use brute force algorithm
     *
     * @param arr
     * @param targetSum
     * @return
     */
    public static int searchTriplet1(int[] arr, int targetSum) {
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                for (int k = j + 1; k < arr.length; ++k) {
                    int diff = targetSum - arr[i] - arr[j] - arr[k];
                    if (diff == 0) {
                        return targetSum;
                    }

                    if (Math.abs(diff) < Math.abs(minDistance)) {
                        minDistance = diff;
                    }
                }
            }
        }

        return targetSum - minDistance;
    }

    /**
     * Using backtracking algorithm to iterate all cases
     *
     * @param arr
     * @param targetSum
     * @return
     */
    public static int searchTriplet0(int[] arr, int targetSum) {
        searchTriplet0(arr, targetSum, 0, 0, new ArrayList<>());

        return targetSum - smallestDistance;
    }

    public static void searchTriplet0(int[] arr, int targetSum, int sum, int num, List<Integer> triplets) {
        if (triplets.size() == 3) {
            int diff = targetSum - sum;
            if (Math.abs(diff) < Math.abs(smallestDistance)) {
                smallestDistance = diff;
            }

            return;
        }

        for (int i = num; i < arr.length; ++i) {
            triplets.add(arr[i]);
            searchTriplet0(arr, targetSum, sum + arr[i], i + 1, triplets);
            triplets.remove(triplets.size() - 1);
        }
    }

    /**
     * Using two pointers technique
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchTriplet(int[] nums, int target) {
        int minDistance = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i <= nums.length - 3; ++i) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int diff = target - nums[i] - nums[left] - nums[right];
                if (diff == 0) {
                    return target - diff;
                }

                if (Math.abs(diff) < Math.abs(minDistance)
                                    || (Math.abs(diff) == Math.abs(minDistance) && diff > minDistance)) {
                    minDistance = diff;
                }

                if (diff > 0) {
                    ++left;
                } else {
                    --right;
                }
            }
        }

        return target - minDistance;
    }

}
