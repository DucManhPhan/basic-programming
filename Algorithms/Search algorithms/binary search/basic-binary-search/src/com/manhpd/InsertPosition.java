package com.manhpd;

public class InsertPosition {
    public static void main(String[] args) {
        int[] nums = {2, 4, 5, 9, 11};
        int[] ks = {5, 3, 15, -1};
        int[] exps = {2, 1, 5, 0};

        for (int i = 0; i < ks.length; ++i) {
            int k = ks[i];
            int exp = exps[i];

            int res = insertPosition(nums, k);
            System.out.println("With k = " + k + ": res = " + res + ", expectation = " + exp);
        }
    }

    private static int insertPosition(int[] nums, int k) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= k) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (left < nums.length && nums[left] >= k) {
            return left;
        }

        return right;
    }
}
