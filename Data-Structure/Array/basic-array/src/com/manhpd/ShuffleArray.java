package com.manhpd;

import java.util.Arrays;

public class ShuffleArray {

    public static void main(String[] args) {
//        int[] nums = {2,5,1,3,4,7};
//        int n = 3;

        int[] nums = {1, 2, 3, 4, 4, 3, 2, 1};
        int n = 4;

        ShuffleArray solution = new ShuffleArray();
        System.out.println(Arrays.toString(solution.shuffle(nums, n)));
    }

    public int[] shuffle(int[] nums, int n) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        int[] res = new int[nums.length];
        int left = 0;
        int right = n;

        for (int i = 0; i < nums.length;) {
            res[i++] = nums[left++];
            res[i++] = nums[right++];
        }

        return res;
    }

}
