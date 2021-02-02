package com.manhpd;

import java.util.HashMap;
import java.util.Map;

public class NumberGoodPairs {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 1, 1, 3};
        int[] nums = {1, 2, 3};

        NumberGoodPairs solution = new NumberGoodPairs();
        System.out.println(solution.numIdenticalPairsI(nums));
    }

    /**
     * Using brute force algorithm
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        return 0;
    }

    /**
     * Using HashMap
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairsI(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        int count = 0;

        for (int value : nums) {
            if (!counter.containsKey(value)) {
                counter.put(value, 1);
            } else {
                count += counter.get(value);
                counter.put(value, counter.get(value) + 1);
            }
        }

        return count;
    }


}
