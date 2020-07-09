package com.manhpd;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
 *
 * Ex1: Input: [-3, 0, 1, 2, -1, 1, -2]
 *      Output: [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
 *      Explanation: There are four unique triplets whose sum is equal to zero.
 *
 * Ex2: Input: [-5, 2, -1, -2, 3]
 *      Output: [[-5, 2, 3], [-2, -1, 3]]
 *      Explanation: There are two unique triplets whose sum is equal to zero.
 *
 */
public class TripletSumZero {

    public static void main(String[] args) {
//        int[] arr = {-3, 0, 1, 2, -1, 1, -2};
        int[] arr = {-5, 2, -1, -2, 3};

//        List<List<Integer>> results = searchTriplets(arr);
        List<List<Integer>> results = threeSum(arr);
//        List<List<Integer>> results = searchTripletsNormal(arr);
        System.out.println("Size of result: " + results.size());
        results.stream().forEach(items -> {
            for (Integer item : items) {
                System.out.print(item + ", ");
            }

            System.out.println();
        });
    }

    public static List<List<Integer>> searchTriplets(int[] arr) {
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; ++i) {
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }

            searchPair(arr, -arr[i], i + 1, triplets);
        }

        return triplets;
    }

    /**
     * Using hashmap to get all a + b + c = sum
     *
     * @param arr
     * @param targetSum
     * @param left
     * @param triplets
     */
    private static void searchPair(int[] arr, int targetSum, int left, List<List<Integer>> triplets) {
        Map<Integer, Integer> valueIndexMap = new HashMap<>();

        for (int i = left; i < arr.length; ++i) {
            if (valueIndexMap.containsKey(targetSum - arr[i])) {
                List<Integer> tmp = new ArrayList<>(Arrays.asList(-targetSum, arr[i], targetSum - arr[i]));
                if (triplets.size() > 0 && tmp.equals(triplets.get(triplets.size() - 1))) {
                    continue;
                }

                triplets.add(tmp);
            }

            valueIndexMap.put(arr[i], i);
        }
    }

    /**
     * Using two pointer technique
     *
     * @param arr
     * @param targetSum
     * @param left
     * @param triplets
     */
    private static void searchPairII(int[] arr, int targetSum, int left, List<List<Integer>> triplets) {
        int right = arr.length - 1;
        while (left < right) {
            int currentSum = arr[left] + arr[right];
            if (currentSum == targetSum) { // found the triplet
                triplets.add(Arrays.asList(-targetSum, arr[left], arr[right]));
                left++;
                right--;
                while (left < right && arr[left] == arr[left - 1])
                    left++; // skip same element to avoid duplicate triplets
                while (left < right && arr[right] == arr[right + 1])
                    right--; // skip same element to avoid duplicate triplets
            } else if (targetSum > currentSum)
                left++; // we need a pair with a bigger sum
            else
                right--; // we need a pair with a smaller sum
        }
    }

    /**
     * Time complexity: O(n^3)
     *
     * @param arr
     * @return
     */
    public static List<List<Integer>> searchTripletsNormal(int[] arr) {
        List<List<Integer>> triplets = new ArrayList<>();
//        Set<Integer> uniqueSet = new HashSet<>(Arrays.asList(arr));
//        Integer[] arrTmp = uniqueSet.toArray(new Integer[uniqueSet.size()]);

        for (int i = 0; i < arr.length - 2; ++i) {
            for (int j = i + 1; j < arr.length - 1; ++j) {
                for (int k = j + 1; k < arr.length; ++k) {
                    List<Integer> res = new ArrayList<>();
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        res.add(arr[i]);
                        res.add(arr[j]);
                        res.add(arr[k]);

                        triplets.add(res);
                    }
                }
            }
        }

        return triplets;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null)
            return null;
        Arrays.sort(nums);
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length - 2; i++) {
            int begin = i + 1;
            int end = nums.length - 1;

            while (begin < end) {
                if (nums[begin] + nums[end] == -nums[i]) {
                    ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(nums[i], nums[end], nums[begin]));
//                    arr.add(nums[i]);
//                    arr.add(nums[end]);
//                    arr.add(nums[begin]);
                    res.add(arr);

                    ++begin;
                    --end;
                } else if (nums[begin] + nums[end] < -nums[i]) {
                    begin++;
                } else {
                    end--;
                }
            }
        }

        return res;
    }

}
