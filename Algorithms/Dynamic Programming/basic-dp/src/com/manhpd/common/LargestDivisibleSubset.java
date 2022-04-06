package com.manhpd.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class LargestDivisibleSubset {

    public static void main(String[] args) {
//        int[] nums = {1,2,4,8};
        int[] nums = {1, 2, 3, 4, 6};
//        int[] nums = {3, 17};

        LargestDivisibleSubset solution = new LargestDivisibleSubset();
        System.out.println(solution.largestDivisibleSubset(nums).toString());
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int[] len = new int[nums.length];
        int[] tracker = new int[nums.length];

        tracker[0] = -1;
        len[0] = 1;

//        Arrays.fill(len, 1);
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; ++i) {
            int maxLen = 1;
            int maxIdx = -1;

            for (int j = i - 1; j >= 0; --j) {
                if (nums[i] % nums[j] == 0 && maxLen < len[j] + 1) {
                    maxLen = len[j] + 1;
                    maxIdx = j;
                }
            }

            len[i] = maxLen;
            tracker[i] = maxIdx;
        }

        int maxLength = Arrays.stream(len).max().getAsInt();
        Integer[] res = new Integer[maxLength];
        int maxLengthIdx = IntStream.range(0, len.length)
                                    .reduce((i, j) -> len[i] > len[j] ? i : j)
                                    .getAsInt();
        int curIdx = 0;

        while (maxLengthIdx != -1) {
            res[curIdx] = nums[maxLengthIdx];
            maxLengthIdx = tracker[maxLengthIdx];
            ++curIdx;
        }

        return Arrays.asList(res);
    }

}
