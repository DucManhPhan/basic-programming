package com.manhpd;

public class LowerBound {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 2, 4, 8};
//        int[] nums = {1, 2, 3, 4, 8};
        int target = 2;

        int res = lowerBound(nums, target);
        System.out.println("Result: " + res);
    }

    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (left < nums.length && nums[left] >= target) {
            return left;
        }

        if (right < nums.length && nums[right] >= target) {
            return right;
        }

        return -1;
    }
}
